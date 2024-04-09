package com.myshop_application

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myshop_application.databinding.ActivityMainBinding
import com.myshop_application.list.ProductListAdapter
import com.myshop_application.repository.ProductRepositoryImpl
import com.myshop_application.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.ViewModelFactory(ProductRepositoryImpl())
    }
    private val adapter = ProductListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter
        val token = intent.getStringExtra("token")

        if (token.isNullOrBlank()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        getList()
        viewModel.list.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun getList() {
        viewModel.getList()
    }
}