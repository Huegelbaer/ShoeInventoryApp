package com.udacity.shoestore.screens.shoelist

import androidx.lifecycle.*
import com.udacity.shoestore.models.Shoe

class ShoeViewModel: ViewModel() {

    private val _selected = MutableLiveData<Shoe>()

    enum class ListEvent {
        CREATE, EDIT, NONE
    }

    enum class DetailsEvent {
        CANCEL, SAVE, NONE
    }

    private var _shoeList = MutableLiveData<List<Shoe>>(emptyShoeList())
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    private var _listEvent = MutableLiveData<ListEvent>(ListEvent.NONE)
    val listEvent: LiveData<ListEvent>
        get() = _listEvent

    private var _detailsEvent = MutableLiveData<DetailsEvent>(DetailsEvent.NONE)
    val detailsEvent: LiveData<DetailsEvent>
        get() = _detailsEvent

    var selected: Shoe
        set(value) { _selected.value = value }
        get () = _selected.value!!

    fun onAdd() {
        _selected.value = emptyShoe()
        _listEvent.value = ListEvent.CREATE
    }

    fun onEdit(shoe: Shoe) {
        _selected.value = shoe
        _listEvent.value = ListEvent.EDIT
    }

    fun onListEventCompleted() {
        _listEvent.value = ListEvent.NONE
    }

    fun onSave() {
        val list = (_shoeList.value ?: emptyShoeList()).toMutableList()
        list.add(selected)
        _shoeList.value = list
        _detailsEvent.value = DetailsEvent.SAVE
    }

    fun onCancel() {
        _detailsEvent.value = DetailsEvent.CANCEL
    }

    fun onDetailsEventCompleted() {
        _detailsEvent.value = DetailsEvent.NONE
    }

    private fun emptyShoeList(): List<Shoe> {
        return emptyList<Shoe>()
    }

    private fun emptyShoe(): Shoe {
        return Shoe("", 0.0, "", "")
    }
}