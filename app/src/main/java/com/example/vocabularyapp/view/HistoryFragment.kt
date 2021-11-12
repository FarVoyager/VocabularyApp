package com.example.vocabularyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.vocabularyapp.AppState
import com.example.vocabularyapp.R
import com.example.vocabularyapp.databinding.FragmentHistoryBinding
import com.example.vocabularyapp.databinding.FragmentMainBinding
import com.example.vocabularyapp.model.DataModel
import com.example.vocabularyapp.view.historyRv.HistoryRvAdapter
import com.example.vocabularyapp.viewModel.HistoryViewModel
import com.example.vocabularyapp.viewModel.WordListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment(R.layout.fragment_history) {

    private val model: HistoryViewModel by viewModel()
    private val binding: FragmentHistoryBinding by viewBinding()

    private var adapter: HistoryRvAdapter? = null
    private val onListItemClickListener: HistoryRvAdapter.OnListItemClickListener =
        object : HistoryRvAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                Toast.makeText(requireContext(), data.text, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.subscribe().observe(viewLifecycleOwner, { renderData(it) })
        model.getData("word", true)

    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                val historyData = data.data
                if (historyData.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "История пуста", Toast.LENGTH_SHORT).show()
                } else {
                    if (adapter == null) {

                        binding.historyRv.layoutManager = LinearLayoutManager(requireContext()).apply {
                            reverseLayout = true
                            stackFromEnd = true
                        }
                        binding.historyRv.adapter = HistoryRvAdapter(onListItemClickListener, historyData)
                    } else {
                        adapter!!.setData(historyData)
                    }
                }
            }
            is AppState.Error -> {
                println("AppState.Error")

            }
            is AppState.Loading -> {
                println("AppState.Loading")
            }
        }
}

companion object {
    @JvmStatic
    fun newInstance() = HistoryFragment()
}
}