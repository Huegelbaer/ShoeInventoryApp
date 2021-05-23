package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WelcomeViewModelFactory(private val username: String) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
            return WelcomeViewModel(username) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}