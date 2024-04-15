package com.myshop_application

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myshop_application.databinding.ActivityCartBinding
import com.myshop_application.list.CartItemHandler
import com.myshop_application.list.CartListAdapter
import com.myshop_application.model.Cart
import com.myshop_application.repository.CartRepositoryImpl
import com.myshop_application.util.PreferenceUtil
import com.myshop_application.viewModel.DetailViewModel
import java.text.DecimalFormat

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private val adapter = CartListAdapter(Handler())
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.ViewModelFactory(CartRepositoryImpl())
    }
    private lateinit var preferences: PreferenceUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter
        preferences = PreferenceUtil(this)
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        getCart()
    }

    private fun observeViewModel() {
        viewModel.list.observe(this) {
            adapter.submitList(it)
            binding.orderButton.text = "주문하기\n" +
                    "${DecimalFormat("#,###").format(viewModel.price) + "원"}"
        }
    }

    private fun getCart() {
        val member = preferences.getMember("member")
        val cart = Cart(member_id = member!!.email)
        viewModel.getCarts(cart)
    }

    fun goHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun goOrder() {
        startActivity(Intent(this, OrderActivity::class.java))
        finish()
    }

    inner class Handler : CartItemHandler {
        override fun onDeleteItem(item: Cart) {
            viewModel.deleteCart(item)
        }

    }
}