package com.example.vocabularyapp.view.main

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi

import com.example.vocabularyapp.databinding.DialogTranslationBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchDialogFragment: BottomSheetDialogFragment() {

    private var _binding: DialogTranslationBinding? = null
    private val binding get() = _binding!!
    private var onSearchClickListener: OnSearchClickListener? = null
    private val textWatcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (!binding.searchEditText.text.isNullOrEmpty()) {
                binding.searchButtonTextview.isEnabled = true
                binding.clearTextImageview.visibility = View.VISIBLE
            } else {
                binding.searchButtonTextview.isEnabled = false
                binding.clearTextImageview.visibility = View.GONE
            }
        }


        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogTranslationBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButtonTextview.setOnClickListener(onSearchButtonClickListener)
        binding.searchEditText.addTextChangedListener(textWatcher)
        addOnClearClickListener()
        setBackgroundBlurEffect()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun setBackgroundBlurEffect() {
        parentFragment?.view?.setRenderEffect(RenderEffect.createBlurEffect(5f, 5f, Shader.TileMode.REPEAT))
        activity?.window?.decorView?.setRenderEffect(RenderEffect.createBlurEffect(5f, 5f, Shader.TileMode.REPEAT))
    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onStop() {
        parentFragment?.view?.setRenderEffect(null)
        activity?.window?.decorView?.setRenderEffect(null)
        super.onStop()
    }

    private fun addOnClearClickListener() {
        binding.clearTextImageview.setOnClickListener {
            binding.searchEditText.setText("")
            binding.searchButtonTextview.isEnabled = false
        }
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }


    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(binding.searchEditText.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    companion object {
        fun newInstance(): SearchDialogFragment = SearchDialogFragment()
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
    }
}