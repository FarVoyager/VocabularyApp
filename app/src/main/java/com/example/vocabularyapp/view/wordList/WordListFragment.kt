package com.example.vocabularyapp.view.wordList

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.model.AppState
import com.example.model.DataModel
import com.example.vocabularyapp.R
import com.example.vocabularyapp.databinding.FragmentMainBinding
import com.example.vocabularyapp.utils.OnlineLiveData
import com.example.vocabularyapp.utils.viewById
import com.example.vocabularyapp.view.main.SearchDialogFragment
import com.example.vocabularyapp.viewModel.WordListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope


class WordListFragment : Fragment(R.layout.fragment_main) {

    private val model: WordListViewModel by viewModel()
    private val binding: FragmentMainBinding by viewBinding()

    private var isNetworkAvailable = true

    private var adapter: MainAdapter? = null
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(requireContext(), data.text, Toast.LENGTH_SHORT).show()
            }
        }

    private val searchFab by viewById<FloatingActionButton>(R.id.search_fab)
    private val searchDialogFragment = SearchDialogFragment.newInstance()

    private fun subscribeToNetworkChange() {
        OnlineLiveData(requireContext()).observe(viewLifecycleOwner, {
            val oldState = isNetworkAvailable
            isNetworkAvailable = it
            if (!isNetworkAvailable) {
                Toast.makeText(requireContext(),"device is offline", Toast.LENGTH_SHORT).show()
            } else if (isNetworkAvailable != oldState) {
                Toast.makeText(requireContext(),"internet connection restored", Toast.LENGTH_SHORT).show()

            }
        })
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToNetworkChange()

        // Подписываемся на изменения MainViewModel
        model.subscribe().observe(viewLifecycleOwner, { renderData(it) })

        // Обработка нажатия fab
        searchFab.setOnClickListener {
            initDialogFragment()
        }
    }

    private fun initDialogFragment() {
        searchDialogFragment.setOnSearchClickListener(object :
            SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                // У ViewModel мы получаем LiveData через метод getData
                model.getData(searchWord, isNetworkAvailable)
            }
        })
        searchDialogFragment.show(childFragmentManager,
            BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
        )
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel.isNullOrEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(requireContext())
                        binding.mainActivityRecyclerview.adapter = MainAdapter(onListItemClickListener, dataModel)
                    } else {
                        adapter!!.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = View.VISIBLE
                    binding.progressBarRound.visibility = View.GONE
                    binding.progressBarHorizontal.progress = appState.progress!!
                } else {
                    binding.progressBarHorizontal.visibility = View.GONE
                    binding.progressBarRound.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.frameTextView.text = "Ничего не найдено"
//        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            initDialogFragment()

        }
    }

    private fun showViewSuccess() {
        binding.frameStartSearch.visibility = View.GONE
        binding.successLinearLayout.visibility = View.VISIBLE
        binding.loadingFrameLayout.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.VISIBLE
        binding.frameStartSearch.visibility = View.GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = View.GONE
        binding.loadingFrameLayout.visibility = View.GONE
        binding.frameStartSearch.visibility = View.VISIBLE
        binding.reloadButton.visibility = View.VISIBLE
    }


    override fun onResume() {
        super.onResume()
        if (!isNetworkAvailable) {
            Toast.makeText(requireContext(), getString(R.string.NO_INT_MSG), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
        @JvmStatic
        fun newInstance() = WordListFragment()
    }
}