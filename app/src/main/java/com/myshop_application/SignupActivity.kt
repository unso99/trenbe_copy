package com.myshop_application

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.myshop_application.databinding.ActivitySignupBinding
import com.myshop_application.model.Member
import com.myshop_application.repository.MemberRepositoryImpl
import com.myshop_application.viewModel.SignupViewModel

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    private val viewModel: SignupViewModel by viewModels {
        SignupViewModel.ViewModelFactory(MemberRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        binding.lifecycleOwner = this
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.isSuccess.observe(this) {
            if (it) {
                Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    fun signup() {
        val dto = Member(
            email = binding.emailEditTextView.text.toString(),
            password = binding.passwordEditTextView.text.toString(),
            name = binding.nameEditTextView.text.toString(),
            phone = binding.phoneEditTextView.text.toString(),
            nickname = binding.nickNameEditTextView.text.toString(),
            birth = binding.birthEditTextView.text.toString().toInt()

        )
        viewModel.signup(dto)
    }
}