package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    private lateinit var binding: ShoeListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.shoe_list_fragment,
            container,
            false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory)
            .get(ShoeListViewModel::class.java)

        binding.floatingActionButton.setOnClickListener {
            viewModel.addShoe()
        }

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            removeAllListItems()
            for (shoe in it) {
                addListItem(shoe)
            }
        })

        return binding.root
    }

    private fun removeAllListItems() {
        binding.linearLayout.removeAllViews()
    }

    private fun addListItem(shoe: Shoe) {
        val subView = TextView(context)
        subView.text = shoe.name
        binding.linearLayout.addView(subView)
    }
}