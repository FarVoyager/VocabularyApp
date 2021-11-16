package com.example.vocabularyapp.view.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.DataModel
import com.example.vocabularyapp.databinding.HistoryRvItemBinding

class HistoryRvAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>
    ):
RecyclerView.Adapter<HistoryRvAdapter.HistoryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = HistoryRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(private val binding: HistoryRvItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                binding.historyRvItemWord.text = data.text
                binding.historyRvItemTranslation.text = data.meanings?.get(0)?.translation?.translation
                itemView.setOnClickListener { open(data) }
            }
        }


    }

    private fun open(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }

}