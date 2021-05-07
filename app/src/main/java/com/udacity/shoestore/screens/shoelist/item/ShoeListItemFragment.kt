package com.udacity.shoestore.screens.shoelist.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListItemFragmentBinding

class ShoeListItemFragment : Fragment() {

    private lateinit var viewModel: ShoeListItemViewModel
    private lateinit var binding: ShoeListItemFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_list_item_fragment,
            container,
            false
        )

/*
        val shoe = arguments?.getParcelable<Shoe>("shoe")

        val subViewModelFactory = ShoeListItemViewModelFactory(shoe!!)
        viewModel = ViewModelProvider(this, subViewModelFactory)
            .get(ShoeListItemViewModel::class.java)
*/

        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory)
            .get(ShoeListItemViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }
}