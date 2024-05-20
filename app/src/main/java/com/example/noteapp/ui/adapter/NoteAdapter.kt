package com.example.noteapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.ItemNoteBinding

class NoteAdapter:ListAdapter<NoteModel , NoteAdapter.ViewHolder>(DiffUtilCallBack()) {
    class ViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.itemTitle.text = item.title
            binding.itemDescription.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffUtilCallBack: DiffUtil.ItemCallback<NoteModel>(){
    override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
        return oldItem.id == newItem.id

    }
}
