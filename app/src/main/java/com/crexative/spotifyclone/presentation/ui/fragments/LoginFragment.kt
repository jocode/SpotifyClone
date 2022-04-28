package com.crexative.spotifyclone.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.crexative.spotifyclone.R
import com.crexative.spotifyclone.core.Constants
import com.crexative.spotifyclone.databinding.FragmentLoginBinding
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        handleLoginClick()

        onRequestTokenClicked()
    }

    private fun handleLoginClick() {
        binding.btnLogin.setOnClickListener {
            onRequestTokenClicked()
        }
    }

    private fun onRequestTokenClicked() {

        val builder = AuthorizationRequest.Builder(
            Constants.CLIENT_ID, AuthorizationResponse.Type.TOKEN, Constants.REDIRECT_URI
        )
        builder.setScopes(arrayOf("streaming user-top-read"))
        val request = builder.build()
        AuthorizationClient.openLoginActivity(requireActivity(), Constants.REQUEST_CODE, request)

        // TODO: Make Request to get Token
        // findNavController().navigate(R.id.action_loginFragment_to_homeScreenFragment)
    }
}