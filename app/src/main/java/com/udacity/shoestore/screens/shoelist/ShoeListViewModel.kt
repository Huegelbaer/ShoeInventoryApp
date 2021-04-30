package com.udacity.shoestore.screens.shoelist

import androidx.lifecycle.*
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {

    private var _shoeList = MutableLiveData<List<Shoe>>(emptyShoeList())
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    fun addShoe() {
        val shoe = Shoe("Test", 6.0, "Company", "Some text")
        val list = (_shoeList.value ?: emptyShoeList()).toMutableList()
        list.add(shoe)
        _shoeList.value = list
    }

    private fun emptyShoeList(): List<Shoe> {
        return emptyList<Shoe>()
    }
}