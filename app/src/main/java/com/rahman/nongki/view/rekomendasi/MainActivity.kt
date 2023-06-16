package com.rahman.nongki.view.rekomendasi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rahman.nongki.databinding.ActivityMainBinding
import com.rahman.nongki.model.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val place = intent.getStringExtra(DATA)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[MainViewModel::class.java]
        if (place != null){
            mainViewModel.listFavorite.observe(this){
                if (it != null)
                mainViewModel.getDetail(place, it)
            }
            mainViewModel.getReview(place)
        }

        supportActionBar?.hide()
        setContentView(binding.root)

    }

    companion object{
        const val DATA = "data"
    }
}