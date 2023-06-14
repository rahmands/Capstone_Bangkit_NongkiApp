package com.rahman.nongki.view.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rahman.nongki.R
import com.rahman.nongki.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var authViewModel: AuthViewModel
    lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.tvLogin.setOnClickListener{
            findNavController().navigate(R.id.loginFragment)
        }

        binding.signupButton.setOnClickListener {
            register()
        }
        return binding.root
    }

    private fun register(){
        val username = binding.nameEditTextRegister.text.toString()
        val email = binding.emailEditTextRegister.text.toString()
        val password = binding.passwordEditTextRegister.text.toString()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireActivity(),"Isi semua data username, email dan password", Toast.LENGTH_SHORT).show()
        } else if (password.length < 8) {
            Toast.makeText(requireActivity(),"Isi password minimal 8 karakter", Toast.LENGTH_SHORT).show()
        } else {
            authViewModel.register(username, email, password)
        }
    }

}