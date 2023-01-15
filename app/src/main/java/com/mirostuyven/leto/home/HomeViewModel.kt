package com.mirostuyven.leto.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirostuyven.leto.data.QuizRepository
import com.mirostuyven.leto.data.getDatabase
import com.mirostuyven.leto.network.LetoApi
import com.mirostuyven.leto.network.Quiz
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val quizRepository = QuizRepository(getDatabase(application))

    val quizzes = quizRepository.quizzes

    private val _quizzesLoading = MutableLiveData<Boolean>(false)
    val quizzesLoading: LiveData<Boolean>
        get() = _quizzesLoading

    init {
        refreshQuizzes()
    }

    private fun refreshQuizzes() {
        _quizzesLoading.value = true
        viewModelScope.launch {
            try {
                quizRepository.refreshQuizzes()
                _quizzesLoading.value = false
            } catch (e: Exception) {
                println("Exception listing quizzes: $e")
            }
        }
    }
}