package com.rahman.nongki.view.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rahman.nongki.databinding.ActivityAuthBinding
import com.rahman.nongki.model.ViewModelFactory
import com.rahman.nongki.view.main.BottomNavActivity

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        authViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[AuthViewModel::class.java]
        setContentView(binding.root)

        authViewModel.message.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        authViewModel.key.observe(this){
            if (it.isNotEmpty() && it!= "null") {
                moveToMainActivity()
            }
        }



    }

    fun moveToMainActivity(){
        val intent = Intent(this, BottomNavActivity::class.java)
        startActivity(intent)
        finish()
    }
}