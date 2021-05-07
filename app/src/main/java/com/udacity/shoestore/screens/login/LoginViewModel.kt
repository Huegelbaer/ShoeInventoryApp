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
        if (username.isEmpty() || password.isEmpty()) {
            _eventAuthenfication.value = AuthenficationState.FAILED
        } else {
            _eventAuthenfication.value = AuthenficationState.SUCCECCED
        }
    }

    fun registerUser(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            _eventAuthenfication.value = AuthenficationState.FAILED
        } else {
            _eventAuthenfication.value = AuthenficationState.SUCCECCED
        }
    }

    fun onAuthenficationCompleted() {
        _eventAuthenfication.value = AuthenficationState.NONE
    }
}