package com.example.vocabularyapp.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.model.AppState
import com.example.vocabularyapp.R
import com.example.vocabularyapp.databinding.FragmentDetailsBinding
import com.example.vocabularyapp.viewModel.DetailsViewModel
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import kotlin.random.Random


class DetailsFragment : Fragment(R.layout.fragment_details), AndroidScopeComponent {

    override val scope: Scope by fragmentScope()
    private val model: DetailsViewModel by scope.inject()
    private val binding: FragmentDetailsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.subscribe().observe(viewLifecycleOwner, { renderData(it) })
        model.getData(arguments?.getString(WORD_KEY).toString(), true)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.detailsHeader.text = appState.data?.get(0)?.text
                binding.detailsTranslation.text = appState.data?.get(0)?.meanings?.get(0)?.translation?.translation
                setGif()
            }
            is AppState.Error -> println("AppState.Error")
            is AppState.Loading -> println("AppState.Loading")
        }
    }

    private fun setGif() {
        val number = Random.nextInt(30)
        when {
            number < 10 -> Glide.with(this).load(R.drawable.best_gif1).into(binding.detailsImageContainer)
            number in 10..19 -> Glide.with(this).load(R.drawable.best_gif2).into(binding.detailsImageContainer)
            else -> Glide.with(this).load(R.drawable.best_gif3).into(binding.detailsImageContainer)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(word: String?): Fragment {
            val bundle = Bundle()
            bundle.apply {
                putString(WORD_KEY, word)
            }
            return DetailsFragment().apply { arguments = bundle }
        }

        const val WORD_KEY = "WORD_KEY"
    }
}