package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel(name: String) : ViewModel() {

    private var _username = MutableLiveData<String>(name)
    val username: LiveData<String>
        get() = _username

    private var _eventContinue = MutableLiveData<Boolean>(false)
    val eventContinue: LiveData<Boolean>
        get() = _eventContinue

    fun pressedButton() {
        _eventContinue.value = true
    }

    fun onContinueCompleted() {
        _eventContinue.value = false
    }
}