package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )

        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory)
            .get(LoginViewModel::class.java)

        viewModel.eventAuthentication.observe(viewLifecycleOwner,  { state ->
            handleAuthenticationChanged(state)
        })

        binding.loginButton.setOnClickListener {
            loginUser()
        }

        binding.registrationButton.setOnClickListener {
            registerUser()
        }

        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.logout_button)
        item?.isVisible = false
    }

    private fun loginUser() {
        val username = binding.editEmailAddress.text.toString()
        val password = binding.editPassword.text.toString()
        viewModel.loginUser(username, password)
    }

    private fun registerUser() {
        val username = binding.editEmailAddress.text.toString()
        val password = binding.editPassword.text.toString()
        viewModel.registerUser(username, password)
    }

    private fun handleAuthenticationChanged(state: LoginViewModel.AuthenticationState) {
        when (state) {
            LoginViewModel.AuthenticationState.SUCCEEDED -> {
                handleAuthenticationSucceeded()
            }
            LoginViewModel.AuthenticationState.FAILED -> {
                handleAuthenticationFailed()
            }
            else -> {
            }
        }
    }

    private fun handleAuthenticationSucceeded() {
        binding.errorLabel.visibility = View.GONE
        navigateToWelcome()
        viewModel.onAuthenticationCompleted()
    }

    private fun handleAuthenticationFailed() {
        binding.errorLabel.visibility = View.VISIBLE
        viewModel.onAuthenticationCompleted()
    }

    private fun navigateToWelcome() {
        val username = binding.editEmailAddress.text.toString()
        val directions = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username)
        findNavController().navigate(directions)
    }

}