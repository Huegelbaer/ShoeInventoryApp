package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.databinding.ShoeListItemFragmentBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeViewModel by activityViewModels()

    private lateinit var binding: ShoeListFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout_button -> {
                navigateToLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun removeAllListItems() {
        binding.linearLayout.removeAllViews()
    }

    private fun addListItem(shoe: Shoe) {
        val itemBinding = ShoeListItemFragmentBinding.inflate(layoutInflater)
        itemBinding.shoeModel = shoe

        itemBinding.root.setOnClickListener {
            selectedListItem(shoe)
        }
        
        binding.linearLayout.addView(itemBinding.root)
    }

    private fun selectedListItem(shoe: Shoe) {
        viewModel.selected = shoe
        navigateToDetails()
    }

    private fun navigateToDetails() {
        findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
    }

    private fun navigateToLogin() {
        findNavController().popBackStack(R.id.loginFragment, false)
    }
}