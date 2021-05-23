package com.udacity.shoestore.screens.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    enum class ListEvent {
        CREATE, EDIT, NONE
    }

    enum class DetailsEvent {
        CANCEL, SAVE, ERROR, NONE
    }

    private var _shoeList = MutableLiveData<ArrayList<Shoe>>(emptyShoeList())
    val shoeList: LiveData<ArrayList<Shoe>>
        get() = _shoeList

    private var _listEvent = MutableLiveData<ListEvent>(ListEvent.NONE)
    val listEvent: LiveData<ListEvent>
        get() = _listEvent

    private var _detailsEvent = MutableLiveData<DetailsEvent>(DetailsEvent.NONE)
    val detailsEvent: LiveData<DetailsEvent>
        get() = _detailsEvent

    private val _selected = MutableLiveData<Shoe>()
    var selected: Shoe
        get() = _selected.value!!
        set(value) {
            _selected.value = value
        }

    private var _selectedIndex: Int = -1

    fun onAdd() {
        _selected.value = emptyShoe()
        _listEvent.value = ListEvent.CREATE
    }

    fun onEdit(shoe: Shoe) {
        _selected.value = shoe
        _selectedIndex = _shoeList.value?.indexOf(shoe) ?: -1
        _listEvent.value = ListEvent.EDIT
    }

    fun onListEventCompleted() {
        _listEvent.value = ListEvent.NONE
    }

    fun onSave() {
        if (!isShoeDataValid()) {
            _detailsEvent.value = DetailsEvent.ERROR
            return
        }
        updateList()
        _detailsEvent.value = DetailsEvent.SAVE
    }

    fun onCancel() {
        _detailsEvent.value = DetailsEvent.CANCEL
    }

    fun onDetailsEventCompleted() {
        _detailsEvent.value = DetailsEvent.NONE
    }

    private fun isShoeDataValid(): Boolean {
        if (selected.name.isEmpty()|| selected.size.isNaN() || selected.company.isEmpty()  || selected.description.isEmpty()) {
            return false
        }
        return true
    }

    private fun updateList() {
        val list = (_shoeList.value ?: emptyShoeList())
        if (_selectedIndex < 0) {
            list.add(selected)
        } else {
            list[_selectedIndex] = selected
        }
        _shoeList.value = list
    }

    private fun emptyShoeList(): ArrayList<Shoe> {
        return ArrayList()
    }

    private fun emptyShoe(): Shoe {
        return Shoe("", 0.0, "", "")
    }
}