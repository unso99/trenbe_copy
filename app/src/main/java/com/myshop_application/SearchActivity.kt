package com.myshop_application

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.myshop_application.databinding.ActivitySearchBinding
import com.myshop_application.list.ProductItemHandler
import com.myshop_application.list.ProductListAdapter
import com.myshop_application.model.Product
import com.myshop_application.repository.CommonRepositoryImpl
import com.myshop_application.repository.ProductRepositoryImpl
import com.myshop_application.util.PreferenceUtil
import com.myshop_application.viewModel.MainViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModel.ViewModelFactory(ProductRepositoryImpl(), CommonRepositoryImpl())
    }
    private val adapter = ProductListAdapter(Handler())
    private val keywordHandler = android.os.Handler(Looper.getMainLooper())
    private lateinit var preferences: PreferenceUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter
        preferences = PreferenceUtil(this)
        val token = preferences.getToken("token")
        binding.keywordEdtiText.addTextChangedListener {
            val runnable = Runnable {
                getList(token!!,it.toString())
            }
            keywordHandler.removeCallbacks(runnable)
            keywordHandler.postDelayed(runnable, 300)
        }


        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.list.observe(this) {
            adapter.submitList(it)
            val resultText = "${it.size}개의 검색결과"
            val spannableString = SpannableString(resultText)
            val colorSpan = ForegroundColorSpan(Color.RED)
            spannableString.setSpan(
                colorSpan,
                0,
                resultText.indexOf("개"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.resultTextView.text = spannableString
            binding.resultTextView.isVisible = true
        }
    }

    private fun getList(token: String, keyword: String) {
        viewModel.getSearchList(token, keyword)
    }

    fun back() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    inner class Handler : ProductItemHandler {
        override fun onClickItem(item: Product) {
            val intent = Intent(this@SearchActivity, DetailActivity::class.java)
            intent.putExtra("item", item)
            startActivity(intent)
            finish()
        }
    }
}