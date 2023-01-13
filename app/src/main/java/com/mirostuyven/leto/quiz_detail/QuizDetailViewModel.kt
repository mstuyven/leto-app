package com.mirostuyven.leto.quiz_detail

import androidx.lifecycle.*
import com.mirostuyven.leto.network.LetoApi
import com.mirostuyven.leto.network.Quiz
import kotlinx.coroutines.launch


class QuizDetailViewModel(private val quizId: String?) : ViewModel() {
    private val _quiz = MutableLiveData<Quiz?>()
    val quiz: LiveData<Quiz?>
        get() = _quiz

    init {
        getQuiz()
    }

    private fun getQuiz() {
        viewModelScope.launch {
            try {
                if (quizId == null) {
                    throw Exception("quizId is not set")
                }
                val data = LetoApi.service.quizDetail(quizId).data
                _quiz.postValue(data)
            } catch (e: Exception) {
                println("Exception getting quiz detail: $e")
                _quiz.postValue(null)
            }
        }
    }

}

class QuizDetailViewModelFactory(private val quizId: String?) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizDetailViewModel::class.java)) {
            return QuizDetailViewModel(quizId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}