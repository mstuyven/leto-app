package com.mirostuyven.leto

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mirostuyven.leto.home.QuizListAdapter
import com.mirostuyven.leto.network.Quiz

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Quiz>?) {
    val adapter = recyclerView.adapter as QuizListAdapter
    adapter.submitList(data)
}
