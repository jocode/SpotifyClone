package com.crexative.spotifyclone.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
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
import dagger.hilt.android.AndroidEntryPoint

private val TAG: String = MainActivity::class.java.simpleName

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_SpotifyClone)
        setContentView(binding.root)

        // Define navHostFragment to navController to manage the screens
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        Log.e(TAG, "Token: ${AppPreferences.token}")

        observeDestinationChange()
    }

    private fun navigateMainFragment() {
        try {
            navController.navigate(R.id.action_loginFragment_to_homeScreenFragment)
        } catch (e: Exception) {
            e.printStackTrace()
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

                    navigateMainFragment()
                }
                AuthorizationResponse.Type.ERROR -> {
                    Log.e(TAG, "onActivityResult: Error ${response.error}")
                }
                else -> {
                    Log.e(TAG, "onActivityResult: ${response.code} ${response.error}")
                }
            }
        }
    }
}