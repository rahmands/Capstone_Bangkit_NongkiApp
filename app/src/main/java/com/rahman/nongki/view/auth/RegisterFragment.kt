package com.rahman.nongki.view.auth

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.rahman.nongki.R
import com.rahman.nongki.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var authViewModel: AuthViewModel
    lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        authViewModel.apply {
            isButtonEnable.observe(viewLifecycleOwner) {
                binding.signupButton.isEnabled = it
            }
        }

        binding.apply {
            binding.signupButton.setOnClickListener {
                val username = binding.nameEditTextRegister.text.toString()
                val email = binding.emailEditTextRegister.text.toString()
                val password = binding.passwordEditTextRegister.text.toString()

                if (username.isEmpty()) {
                    binding.nameEditTextRegister.error = "Nama Harus Diisi"
                    binding.nameEditTextRegister.requestFocus()
                    return@setOnClickListener
                }


                //Validasi email
                if (email.isEmpty()) {
                    binding.emailEditTextRegister.error = "Email Harus Diisi"
                    binding.emailEditTextRegister.requestFocus()
                    return@setOnClickListener
                }

                //Validasi email tidak sesuai
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.emailEditTextRegister.error = "Email Tidak Valid"
                    binding.emailEditTextRegister.requestFocus()
                    return@setOnClickListener
                }

                //Validasi password
                if (password.isEmpty()) {
                    binding.passwordEditTextRegister.error = "Password Harus Diisi"
                    binding.passwordEditTextRegister.requestFocus()
                    return@setOnClickListener
                }

                //Validasi panjang password
                if (password.length < 8) {
                    binding.passwordEditTextRegister.error = "Password Minimal 6 Karakter"
                    binding.passwordEditTextRegister.requestFocus()
                    return@setOnClickListener
                }

                authViewModel.apply {
                    register(username, email, password)
                    registerResponse.observe(viewLifecycleOwner) {
                        moveToLogin()
                    }
                    isError.observe(viewLifecycleOwner) {
                        showError(it)
                    }
                }
            }
            binding.tvLogin.setOnClickListener {
                it.findNavController().navigate(R.id.loginFragment)
            }
        }
    }


    private fun moveToLogin() {
        val loginFragment = LoginFragment()
        val fragmentManager = parentFragmentManager

        fragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, loginFragment, LoginFragment::class.java.simpleName)
            addToBackStack(null)
            commit()

            //val navController = findNavController(R.id.fragmentContainerView)
            //navController.navigate(R.id.loginFragment)
        }
    }

    private fun showError(text: String){
        Snackbar.make(
            binding.root, text, Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null!!
    }
}