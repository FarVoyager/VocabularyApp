package com.example.vocabularyapp.view.wordList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.DataModel
import com.example.vocabularyapp.databinding.RecyclerViewItemBinding

class MainAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>
): RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        val binding =
            RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(private val binding: RecyclerViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.headerTextviewRecyclerItem.text = data.text
                binding.descriptionTextviewRecyclerItem.text =
                    data.meanings?.get(0)?.translation?.translation
                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}