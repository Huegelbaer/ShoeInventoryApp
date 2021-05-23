package com.udacity.shoestore.screens.shoelist

import androidx.lifecycle.*
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _selected = MutableLiveData<Shoe>()

    private var _selectedIndex: Int = -1

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

    var selected: Shoe
        set(value) {
            _selected.value = value
        }
        get() = _selected.value!!

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

    fun onSave(name: String, size: Double?, company: String, description: String) {
        if (!isShoeDataValid(name, size, company, description)) {
            _detailsEvent.value = DetailsEvent.ERROR
            return
        }
        selected = Shoe(name, size!!, company, description)
        updateList()
        _detailsEvent.value = DetailsEvent.SAVE
    }

    fun onCancel() {
        _detailsEvent.value = DetailsEvent.CANCEL
    }

    fun onDetailsEventCompleted() {
        _detailsEvent.value = DetailsEvent.NONE
    }

    private fun isShoeDataValid(name: String, size: Double?, company: String, description: String): Boolean {
        if (name.isEmpty()|| size == null || company.isEmpty()  || description.isEmpty()) {
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