package com.myshop_application

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.myshop_application.databinding.ActivityMainBinding
import com.myshop_application.list.ProductListAdapter
import com.myshop_application.model.Common
import com.myshop_application.model.Product
import com.myshop_application.repository.CommonRepositoryImpl
import com.myshop_application.repository.ProductRepositoryImpl
import com.myshop_application.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.ViewModelFactory(ProductRepositoryImpl(),CommonRepositoryImpl())
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
        initView()
        observeViewModel()
    }

    private fun initView() {
        viewModel.getCommonList(Common(p_code_id = 1000))
        viewModel.commonList.observe(this) {
            it.forEach { common ->
                val tab = binding.tabLayout.newTab()
                tab.text = common.code_name

                binding.tabLayout.addTab(tab)
            }
        }
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.text) {
                    "All" -> {
                        getList(Product())
                    }

                    "니트웨어" -> {
                        getList(Product(category = "1001"))
                    }

                    "티셔츠/맨투맨" -> {
                        getList(Product(category = "1002"))
                    }

                    "셔츠" -> {
                        getList(Product(category = "1003"))
                    }

                    "자켓" -> {
                        getList(Product(category = "1004"))
                    }

                    "블레이저/수트" -> {
                        getList(Product(category = "1005"))
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                adapter.submitList(emptyList())
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun observeViewModel() {
        getList(Product())
        viewModel.list.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun getList(body : Product) {
        viewModel.getList(body)
    }
}