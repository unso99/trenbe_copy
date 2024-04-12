package com.myshop_application

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myshop_application.databinding.ActivityOrderBinding
import com.myshop_application.list.OrderListAdapter
import com.myshop_application.model.Cart
import com.myshop_application.model.Member
import com.myshop_application.model.Order
import com.myshop_application.model.OrderDetail
import com.myshop_application.repository.CartRepositoryImpl
import com.myshop_application.repository.OrderDetailRepositoryImpl
import com.myshop_application.repository.OrderRepositoryImpl
import com.myshop_application.util.PreferenceUtil
import com.myshop_application.viewModel.DetailViewModel
import com.myshop_application.viewModel.OrderDetailViewModel
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
    private val orderDetailViewModel: OrderDetailViewModel by viewModels {
        OrderDetailViewModel.ViewModelFactory(OrderDetailRepositoryImpl())
    }
    private lateinit var preferences: PreferenceUtil
    private var orderId = -1L
    private lateinit var member: Member
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter
        preferences = PreferenceUtil(this)
        initViewModel()
        initView()
        observeViewModel()
    }

    private fun initView() {
        binding.addressInputEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                binding.payButton.isEnabled = s.length > 1
            }

        })
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
        orderViewModel.isSuccess.observe(this) {
            if(it) {
                Toast.makeText(this,"주문 완료 되었습니다.",Toast.LENGTH_SHORT).show()
                detailViewModel.deleteCart(Cart(member_id = member.email))
                finish()
            }
        }
        orderDetailViewModel.isSuccess.observe(this) {
            if (it) {
                val order = Order(
                    id = orderId,
                    member_id = member.email,
                    total_price = detailViewModel.price,
                    address = binding.addressInputEditText.text.toString()
                )
                orderViewModel.addOrder(order)
            }
        }
    }

    private fun initViewModel() {
        member = preferences.getMember("member") as Member
        val cart = Cart(member_id = member!!.email)
        detailViewModel.getCarts(cart)
        orderViewModel.getOrderId()
    }

    fun back() {
        finish()
    }

    fun order() {
        detailViewModel.list.value?.forEach {
            val orderDetail = OrderDetail(
                orders_id = orderId,
                product_id = it.product_id!!
            )
            orderDetailViewModel.addOrderDetail(orderDetail)
        }
    }
}