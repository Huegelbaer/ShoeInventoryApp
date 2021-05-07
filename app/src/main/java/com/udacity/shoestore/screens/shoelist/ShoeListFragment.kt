package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    private lateinit var binding: ShoeListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_list_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this

        binding.floatingActionButton.setOnClickListener {
            viewModel.onAdd()
        }

        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            removeAllListItems()
            for (shoe in it) {
                addListItem(shoe)
            }
        })

        viewModel.listEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                ShoeViewModel.ListEvent.CREATE -> {
                    navigateToDetails()
                    viewModel.onListEventCompleted()
                }
                ShoeViewModel.ListEvent.EDIT -> {
                    navigateToDetails()
                    viewModel.onListEventCompleted()
                }
                else -> {
                }
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

        subView.setOnClickListener {
            selectedListItem(shoe)
        }

        binding.linearLayout.addView(subView)
    }

    private fun selectedListItem(shoe: Shoe) {
        viewModel.selected = shoe
        navigateToDetails()
    }

    private fun navigateToDetails() {
        findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
    }
}