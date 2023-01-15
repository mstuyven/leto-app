package com.mirostuyven.leto.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirostuyven.leto.network.LetoApi
import com.mirostuyven.leto.network.TokenRequest
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    private val _token = MutableLiveData<String?>()

    fun doLogin(onDone: (token: String?) -> Unit): Job {
        return viewModelScope.launch {
            try {
                val emailString = email.value
                val passwordString = password.value
                if (emailString != null && passwordString != null) {
                    val data = LetoApi.service.createSessionToken(
                        TokenRequest(emailString, passwordString)
                    ).data
                    _token.postValue(data)
                    onDone(data)
                }
            } catch (e: Exception) {
                println("Exception getting login token: $e")
                _token.postValue(null)
                this.cancel()
            }
        }
    }
}