package com.myshop_application

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myshop_application.databinding.ActivityDetailBinding
import com.myshop_application.model.Cart
import com.myshop_application.model.Product
import com.myshop_application.repository.CartRepositoryImpl
import com.myshop_application.repository.ImageProvider
import com.myshop_application.util.PreferenceUtil
import com.myshop_application.viewModel.DetailViewModel

class DetailActivity : AppCompatActivity(), ImageProvider.Callback {
    private lateinit var binding: ActivityDetailBinding
    private val imageProvider = ImageProvider(this)
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.ViewModelFactory(CartRepositoryImpl())
    }
    private lateinit var preferenceUtil: PreferenceUtil
    private lateinit var item: Product
    private var token: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        item = intent.getSerializableExtra("item") as Product
        binding.view = this
        binding.item = item
        setContentView(binding.root)
        preferenceUtil = PreferenceUtil(this)
        token = preferenceUtil.getToken("token")
        imageProvider.getImage(token!!,item.imageUrl!!)
        initSpinner()
        observeViewModel()
    }

    override fun getImage(bitmap: Bitmap) {
        binding.imageView.setImageBitmap(bitmap)
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            this@DetailActivity,
            R.array.size_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }
    }

    private fun observeViewModel() {
        viewModel.isSuccess.observe(this) {
            if (it) {
                Toast.makeText(this, "장바구니에 추가 되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "장바구니에 추가 하지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onBack() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun addCart() {
        val member_id = preferenceUtil.getMember("member")?.email ?: ""
        val product_id = item.id
        val cart = Cart(
            member_id = member_id,
            product_id = product_id
        )
        viewModel.addCart(token!!,cart)
    }

    fun goCart() {
        startActivity(Intent(this, CartActivity::class.java))
        finish()
    }

    fun goSearch() {
        startActivity(Intent(this,SearchActivity::class.java))
        finish()
    }
}