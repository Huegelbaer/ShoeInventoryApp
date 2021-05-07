package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    enum class AuthenticationState {
        SUCCEEDED, FAILED, NONE
    }

    private var _eventAuthentication =
        MutableLiveData<AuthenticationState>(AuthenticationState.NONE)
    val eventAuthentication: LiveData<AuthenticationState>
        get() = _eventAuthentication

    fun loginUser(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            _eventAuthentication.value = AuthenticationState.FAILED
        } else {
            _eventAuthentication.value = AuthenticationState.SUCCEEDED
        }
    }

    fun registerUser(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            _eventAuthentication.value = AuthenticationState.FAILED
        } else {
            _eventAuthentication.value = AuthenticationState.SUCCEEDED
        }
    }

    fun onAuthenticationCompleted() {
        _eventAuthentication.value = AuthenticationState.NONE
    }
}