package com.rahman.nongki.view.auth

//import android.util.Patterns
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rahman.nongki.R
import com.rahman.nongki.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var authViewModel: AuthViewModel

    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]

        binding.loginButton.setOnClickListener {
            login()
        }

        binding.tvRegister.setOnClickListener{
            findNavController().navigate(R.id.registerFragment)
        }
        return binding.root
    }

    private fun login() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(requireActivity(),"Isi semua data email dan password", Toast.LENGTH_SHORT).show()
        } else {
            authViewModel.login(email, password)
        }
    }


}