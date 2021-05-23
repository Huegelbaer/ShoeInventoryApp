package com.udacity.shoestore.screens.shoedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.shoelist.ShoeViewModel

class ShoeDetailsFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()
    private lateinit var binding: ShoeDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_detail_fragment,
            container,
            false
        )

        binding.viewModel = viewModel
        binding.shoe = viewModel.selected
        binding.lifecycleOwner = this

        viewModel.detailsEvent.observe(viewLifecycleOwner, Observer { event ->
            when (event) {
                ShoeViewModel.DetailsEvent.SAVE -> {
                    navigateToList()
                    viewModel.onDetailsEventCompleted()
                }
                ShoeViewModel.DetailsEvent.CANCEL -> {
                    navigateToList()
                    viewModel.onDetailsEventCompleted()
                }
                ShoeViewModel.DetailsEvent.ERROR -> {
                    handleError()
                    viewModel.onDetailsEventCompleted()
                }
                else -> {
                }
            }
        })

        return binding.root
    }

    private fun navigateToList() {
        findNavController().navigate(R.id.action_shoeDetailsFragment_to_shoeListFragment)
    }

    private fun handleError() {
        setErrorIfEmpty(binding.editShoeName)
        setErrorIfEmpty(binding.editCompanyName)
        setErrorIfEmpty(binding.editShoeSize)
        setErrorIfEmpty(binding.editDescription)
    }

    private fun setErrorIfEmpty(editView: EditText) {
        if (editView.text.isEmpty()) {
            editView.error = getString(R.string.shoe_details_empty_input_error)
        } else {
            editView.error = null
        }
    }
}