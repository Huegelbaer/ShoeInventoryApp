package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel: ViewModel() {

    enum class AuthenficationState {
        SUCCECCED, FAILED, NONE
    }

    private var _eventAuthenfication = MutableLiveData<AuthenficationState>(AuthenficationState.NONE)
    val eventAuthenfication: LiveData<AuthenficationState>
        get() = _eventAuthenfication

    fun loginUser(username: String, password: String) {
        Timber.d("login user with $username and $password")
        _eventAuthenfication.value = AuthenficationState.SUCCECCED
    }

    fun registerUser(username: String, password: String) {
        Timber.d("register user with $username and $password")
        _eventAuthenfication.value = AuthenficationState.SUCCECCED
    }

    fun onAuthenficationCompleted() {
        _eventAuthenfication.value = AuthenficationState.NONE
    }
}