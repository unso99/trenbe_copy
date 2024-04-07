package com.myshop_application

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.myshop_application.databinding.ActivityLoginBinding
import com.myshop_application.model.Member
import com.myshop_application.repository.MemberRepositoryImpl
import com.myshop_application.viewModel.LoginViewModel
import com.myshop_application.viewModel.SignupViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModel.ViewModelFactory(MemberRepositoryImpl())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        binding.lifecycleOwner = this
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.isSuccess.observe(this) {
            if(it) {
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("token",viewModel.token)
                startActivity(intent)
                finish()
            }else {
                binding.errorTextView.isVisible = true
            }
        }
    }

    fun login() {
        val dto = Member(
            email = binding.emailInputEditText.text.toString(),
            password = binding.passwordInputEditText.text.toString()
        )
        viewModel.login(dto)
    }

    fun goSignUp() {
        startActivity(Intent(this, SignupActivity::class.java))
    }
}