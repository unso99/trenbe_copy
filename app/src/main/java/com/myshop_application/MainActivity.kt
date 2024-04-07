package com.myshop_application

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myshop_application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("token")

        if (token.isNullOrBlank()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}