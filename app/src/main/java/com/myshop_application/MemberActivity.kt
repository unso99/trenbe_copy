package com.myshop_application

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myshop_application.databinding.ActivityMemberBinding
import com.myshop_application.list.MyPageListAdapter
import com.myshop_application.model.Member
import com.myshop_application.repository.CommonRepositoryImpl
import com.myshop_application.repository.MemberRepositoryImpl
import com.myshop_application.util.PreferenceUtil
import com.myshop_application.viewModel.CommonViewModel
import com.myshop_application.viewModel.LoginViewModel

class MemberActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemberBinding
    private val adapter = MyPageListAdapter()
    private val commonViewModel: CommonViewModel by viewModels {
        CommonViewModel.ViewModelFactory(CommonRepositoryImpl())
    }
    private val memberViewModel: LoginViewModel by viewModels {
        LoginViewModel.ViewModelFactory(MemberRepositoryImpl())
    }
    private lateinit var preferences: PreferenceUtil
    private var member = Member()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter
        preferences = PreferenceUtil(this)
        val item = preferences.getMember("member") as Member
        val token = preferences.getToken("token")
        binding.item = item
        commonViewModel.getCategory(token!!,3000)
        observeViewModel()

    }

    private fun observeViewModel() {
        commonViewModel.list.observe(this) {
            adapter.submitList(it)
        }
        memberViewModel.member.observe(this) {
            binding.item = it
        }

    }

    fun goHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    fun goCart() {
        startActivity(Intent(this, CartActivity::class.java))
        finish()
    }

    fun logout() {
        preferences.logout()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}