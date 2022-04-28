package com.crexative.spotifyclone.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.crexative.spotifyclone.R
import com.crexative.spotifyclone.core.AppPreferences
import com.crexative.spotifyclone.core.Constants.REQUEST_CODE
import com.crexative.spotifyclone.core.hide
import com.crexative.spotifyclone.core.visible
import com.crexative.spotifyclone.databinding.ActivityMainBinding
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationResponse

private val TAG: String = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Define navHostFragment to navController to manage the screens
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        setupMainScreen()
        observeDestinationChange()
    }

    private fun setupMainScreen() {
        AppPreferences.token?.let {
            navController.navigate(R.id.action_loginFragment_to_homeScreenFragment)
        }
    }

    private fun observeDestinationChange() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> binding.bottomNavigationView.hide()
                else -> binding.bottomNavigationView.visible()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, data)
            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {

                    AppPreferences.token = response.accessToken
                    Log.e(TAG, "onActivityResult: Token: ${response.accessToken}")

                    // waitForUserInfo()
                }
                AuthorizationResponse.Type.ERROR -> {}
                else -> {}
            }
        }
    }
}