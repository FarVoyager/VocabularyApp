package com.example.vocabularyapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.vocabularyapp.R
import com.example.vocabularyapp.databinding.FragmentDetailsBinding
import com.example.vocabularyapp.databinding.FragmentHistoryBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val binding: FragmentDetailsBinding by viewBinding()


    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}