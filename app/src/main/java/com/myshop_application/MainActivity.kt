package com.myshop_application

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.myshop_application.databinding.ActivityMainBinding
import com.myshop_application.list.ProductItemHandler
import com.myshop_application.list.ProductListAdapter
import com.myshop_application.model.Common
import com.myshop_application.model.Product
import com.myshop_application.repository.CommonRepositoryImpl
import com.myshop_application.repository.ProductRepositoryImpl
import com.myshop_application.util.PreferenceUtil
import com.myshop_application.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.ViewModelFactory(ProductRepositoryImpl(),CommonRepositoryImpl())
    }
    private val adapter = ProductListAdapter(Handler())
    private lateinit var preferences : PreferenceUtil
    private var token : String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.view = this
        binding.recyclerView.adapter = adapter
        preferences = PreferenceUtil(this)
        token = preferences.getToken("token")

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

    fun goCart() {
        startActivity(Intent(this,CartActivity::class.java))
    }

    fun goSearch() {
        startActivity(Intent(this,SearchActivity::class.java))
    }

    inner class Handler : ProductItemHandler {
        override fun onClickItem(item: Product) {
            val intent = Intent(this@MainActivity,DetailActivity::class.java)
            intent.putExtra("item",item)
            intent.putExtra("token",token)
            startActivity(intent)
        }

    }
}