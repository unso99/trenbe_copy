package com.myshop_application

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myshop_application.databinding.ActivityOrderBinding
import com.myshop_application.list.OrderListAdapter
import com.myshop_application.model.Cart
import com.myshop_application.repository.CartRepositoryImpl
import com.myshop_application.repository.OrderRepositoryImpl
import com.myshop_application.util.PreferenceUtil
import com.myshop_application.viewModel.DetailViewModel
import com.myshop_application.viewModel.OrderViewModel
import java.text.DecimalFormat

class OrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderBinding
    private val adapter = OrderListAdapter()
    private val detailViewModel: DetailViewModel by viewModels {
        DetailViewModel.ViewModelFactory(CartRepositoryImpl())
    }
    private val orderViewModel: OrderViewModel by viewModels {
        OrderViewModel.ViewModelFactory(OrderRepositoryImpl())
    }
    private lateinit var preferences: PreferenceUtil
    private var orderId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter
        preferences = PreferenceUtil(this)
        initViewModel()
        observeViewModel()
    }

    private fun observeViewModel() {
        detailViewModel.list.observe(this) {
            adapter.submitList(it)
            binding.payButton.text =
                "${DecimalFormat("#,###").format(detailViewModel.price) + "원"} 결제하기"
        }
        orderViewModel.orderId.observe(this) {
            orderId = it
        }
    }

    private fun initViewModel() {
        val member = preferences.getMember("member")
        val cart = Cart(member_id = member!!.email)
        detailViewModel.getCarts(cart)
        orderViewModel.getOrderId()
    }

    fun back() {
        finish()
    }
}