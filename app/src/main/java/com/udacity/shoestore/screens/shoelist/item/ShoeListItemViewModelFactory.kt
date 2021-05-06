package com.udacity.shoestore.screens.shoelist.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.Shoe

class ShoeListItemViewModelFactory(private val shoe: Shoe) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeListItemViewModel::class.java)) {
            return ShoeListItemViewModel(shoe) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}