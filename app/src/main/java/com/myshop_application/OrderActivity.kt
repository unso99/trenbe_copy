package com.myshop_application

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myshop_application.databinding.ActivityOrderBinding
import com.myshop_application.list.OrderListAdapter
import com.myshop_application.model.Cart
import com.myshop_application.repository.CartRepositoryImpl
import com.myshop_application.util.PreferenceUtil
import com.myshop_application.viewModel.DetailViewModel
import java.text.DecimalFormat

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private val adapter = OrderListAdapter()
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.ViewModelFactory(CartRepositoryImpl())
    }
    private lateinit var preferences : PreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
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
            binding.payButton.text = "${DecimalFormat("#,###").format(viewModel.price) + "원"} 결제하기"
        }
    }

    private fun getCart() {
        val member = preferences.getMember("member")
        val cart = Cart(member_id = member!!.email)
        viewModel.getCarts(cart)
    }
}