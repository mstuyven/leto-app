package com.mirostuyven.leto.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mirostuyven.leto.R
import com.mirostuyven.leto.databinding.QuizItemBinding
import com.mirostuyven.leto.network.Quiz

class QuizListAdapter : ListAdapter<Quiz, QuizListAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private var binding: QuizItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quiz: Quiz) {
            binding.property = quiz
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Quiz>() {
        override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(QuizItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quiz = getItem(position)
        holder.bind(quiz)
    }
}