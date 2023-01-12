package com.mirostuyven.leto.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirostuyven.leto.network.LetoApi
import com.mirostuyven.leto.network.Quiz
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _quizzes = MutableLiveData<List<Quiz>>()
    val quizzes: LiveData<List<Quiz>>
        get() = _quizzes

    init {
      getQuizzes()
    }

    private fun getQuizzes() {
        viewModelScope.launch {
            try {
                val data = LetoApi.service.listQuizzes().data
                _quizzes.postValue(data)
            } catch (e: Exception) {
                println("Exception listing quizzes: $e")
                _quizzes.postValue(listOf())
            }
        }
    }
}