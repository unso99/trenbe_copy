package com.myshop_application

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myshop_application.databinding.ActivityCartBinding
import com.myshop_application.list.CartListAdapter
import com.myshop_application.model.Cart
import com.myshop_application.repository.CartRepositoryImpl
import com.myshop_application.util.PreferenceUtil
import com.myshop_application.viewModel.DetailViewModel

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private val adapter = CartListAdapter()
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.ViewModelFactory(CartRepositoryImpl())
    }
    private lateinit var preferences: PreferenceUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter
        preferences = PreferenceUtil(this)
        getCart()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.list.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun getCart() {
        val member = preferences.getMember("member")
        val cart = Cart(member_id = member!!.email)
        viewModel.getCarts(cart)
    }
}