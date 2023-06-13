package com.rahman.nongki.view.auth

//import android.util.Patterns
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.rahman.nongki.R
import com.rahman.nongki.SettingViewModel
import com.rahman.nongki.data.local.SettingDataStore
import com.rahman.nongki.data.local.SettingViewModelFactory
import com.rahman.nongki.data.local.dataStore
import com.rahman.nongki.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel: AuthViewModel by activityViewModels()

    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        val preferences = SettingDataStore.getInstance((activity as AuthActivity).dataStore)
        val settingViewModel = ViewModelProvider(requireActivity(), SettingViewModelFactory(preferences)
        )[SettingViewModel::class.java]


        authViewModel.apply {
            isButtonEnable.observe(viewLifecycleOwner) {
                binding.tvRegister.isEnabled = it
            }
        }

        binding.loginButton.setOnClickListener {
            email = binding.emailEditText.text.toString()
            password = binding.passwordEditText.text.toString()

            //Validasi email
            if (email.isEmpty()) {
                binding.emailEditText.error = "Email Harus Diisi"
                binding.emailEditText.requestFocus()
                return@setOnClickListener
            }

            //Validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEditText.error = "Email Tidak Valid"
                binding.emailEditText.requestFocus()
                return@setOnClickListener
            }

            //Validasi password
            if (password.isEmpty()) {
                binding.passwordEditText.error = "Password Harus Diisi"
                binding.passwordEditText.requestFocus()
                return@setOnClickListener
            }

            //Validasi panjang password
            if (password.length < 6) {
                binding.passwordEditText.error = "Password Minimal 6 Karakter"
                binding.passwordEditText.requestFocus()
                return@setOnClickListener
            }

            authViewModel.apply {
                login(email, password)
                loginResponse.observe(viewLifecycleOwner){
                    settingViewModel.saveLoginSession(it.data)
                    settingViewModel.saveUserId(it.data)
                    moveToMain()
                }
                isError.observe(viewLifecycleOwner){
                    showError(it)
                }
            }
        }

        binding.tvRegister.setOnClickListener{
            it.findNavController().navigate(R.id.registerFragment)
        }
    }



    private fun moveToMain(){
        (activity as AuthActivity).moveToMainActivity()
    }

    private fun showError(text: String) {
        Snackbar.make(
            binding.root, text, Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}