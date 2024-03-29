package com.crexative.spotifyclone.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.crexative.spotifyclone.R
import com.crexative.spotifyclone.core.AppPreferences
import com.crexative.spotifyclone.core.Constants
import com.crexative.spotifyclone.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        handleLoginClick()

        if (!AppPreferences.token.isNullOrEmpty())
            onRequestTokenClicked()
    }

    private fun handleLoginClick() {
        binding.btnLogin.setOnClickListener {
            onRequestTokenClicked()
        }

        binding.btnCreateAccount.setOnClickListener {
            Snackbar.make(
                requireView(),
                getString(R.string.error_function_not_implemented),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun onRequestTokenClicked() {

        val builder = AuthorizationRequest.Builder(
            Constants.CLIENT_ID, AuthorizationResponse.Type.TOKEN, Constants.REDIRECT_URI
        )
        builder.setScopes(arrayOf("streaming user-top-read"))
        val request = builder.build()
        AuthorizationClient.openLoginActivity(requireActivity(), Constants.REQUEST_CODE, request)
    }
}