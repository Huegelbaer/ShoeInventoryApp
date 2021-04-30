package com.udacity.shoestore.screens.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.WelcomeFragmentBinding

class WelcomeFragment: Fragment() {

    private lateinit var viewModel: WelcomeViewModel
    private lateinit var viewModelFactory: WelcomeViewModelFactory

    private lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.welcome_fragment,
            container,
            false)

        val username = arguments?.getString("username") ?: "User"

        viewModelFactory = WelcomeViewModelFactory(username)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(WelcomeViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.eventContinue.observe(viewLifecycleOwner, Observer { shouldContinue ->
            if (shouldContinue) {
                navigateToInstructions()
                viewModel.onContinueCompleted()
            }
        })

        binding.continueButton.setOnClickListener {
            viewModel.pressedButton()
        }

        return binding.root
    }

    private fun navigateToInstructions() {
        findNavController().navigate(R.id.instructionsFragment)
    }
}