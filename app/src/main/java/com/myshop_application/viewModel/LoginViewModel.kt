package com.myshop_application.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myshop_application.model.Member
import com.myshop_application.repository.MemberRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: MemberRepository) : ViewModel() {
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    var token = ""
    fun login(dto: Member) {
        viewModelScope.launch {
            val response = repository.login(dto)
            if (response.isSuccessful) {
                token = response.body()!!.token
                _isSuccess.postValue(true)
            } else {
                _isSuccess.postValue(false)
            }
        }
    }

    class ViewModelFactory(private val repository: MemberRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(repository) as T
        }
    }

}