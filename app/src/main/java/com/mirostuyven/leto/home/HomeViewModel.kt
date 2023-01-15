package com.mirostuyven.leto.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.mirostuyven.leto.data.QuizRepository
import com.mirostuyven.leto.data.getDatabase
import com.mirostuyven.leto.network.LetoApi
import com.mirostuyven.leto.network.User
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val quizRepository = QuizRepository(getDatabase(application))

    val quizzes = quizRepository.quizzes

    private val _quizzesLoading = MutableLiveData(false)
    val quizzesLoading: LiveData<Boolean>
        get() = _quizzesLoading

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

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

    fun loadUser(token: String) {
        viewModelScope.launch {
            try {
                val id = JWT(token).getClaim("user").asString()
                if (id !== null) {
                    val data = LetoApi.service.userDetail(id).data
                    _user.postValue(data)
                }
            } catch (e: Exception) {
                println("Exception getting user: $e")
            }
        }
    }
}