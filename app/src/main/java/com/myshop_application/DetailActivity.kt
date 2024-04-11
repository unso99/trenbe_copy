package com.myshop_application

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myshop_application.databinding.ActivityDetailBinding
import com.myshop_application.model.Product
import com.myshop_application.repository.ImageProvider

class DetailActivity : AppCompatActivity(),ImageProvider.Callback {
    private lateinit var binding : ActivityDetailBinding
    private val imageProvider = ImageProvider(this)
    private var token : String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val item = intent.getSerializableExtra("item") as Product
        binding.view = this
        binding.item = item
        setContentView(binding.root)
        token = intent.getStringExtra("token")
        imageProvider.getImage(item.imageUrl!!)
    }

    override fun getImage(bitmap: Bitmap) {
        binding.imageView.setImageBitmap(bitmap)
    }

    fun onBack(){
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("token",token)
        startActivity(intent)
        finish()
    }
}