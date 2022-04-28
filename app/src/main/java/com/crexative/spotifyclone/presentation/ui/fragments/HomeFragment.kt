package com.crexative.spotifyclone.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.crexative.spotifyclone.R
import com.crexative.spotifyclone.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
    }
}