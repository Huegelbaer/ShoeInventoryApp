package com.udacity.shoestore.screens.shoelist.item

import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListItemViewModel(val shoe: Shoe) : ViewModel() {

    val name: String = shoe.name

    val company: String = shoe.company

    val size: Double = shoe.size

    val description: String = shoe.description
}