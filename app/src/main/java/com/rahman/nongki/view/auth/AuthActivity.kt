package com.rahman.nongki.view.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.rahman.nongki.R
import com.rahman.nongki.databinding.ActivityAuthBinding
import com.rahman.nongki.model.dto.LoginResponse
import com.rahman.nongki.view.BottomNavigation.BottomNavActivity
import com.rahman.nongki.view.rekomendasi.MainActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var loginFragment: LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun moveToMainActivity(loginResponse: LoginResponse){
        val intent = Intent(this, BottomNavActivity::class.java)
        intent.putExtra("loginResponse", loginResponse as java.io.Serializable)
        startActivity(intent)
        finish()
    }
}