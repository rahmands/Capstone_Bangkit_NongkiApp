package com.rahman.nongki.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rahman.nongki.databinding.FragmentLogoutBinding


class LogoutFragment : Fragment() {
    private lateinit var viewModel: BottomNavViewModel
    private var _binding: FragmentLogoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[BottomNavViewModel::class.java]


        binding.tvLogout.setOnClickListener{
            viewModel.logout()
        }
        return binding.root
    }




}