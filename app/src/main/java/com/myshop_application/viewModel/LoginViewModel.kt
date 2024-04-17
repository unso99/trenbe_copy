package com.myshop_application.viewModel

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

    private val _member = MutableLiveData<Member>()
    val member: LiveData<Member> = _member
    var token = ""
    fun login(dto: Member) {
        viewModelScope.launch {
            val response = repository.login(dto)
            if (response.isSuccessful) {
                token = response.body()!!.token
                _member.postValue(response.body()!!.dto)
                _isSuccess.postValue(true)
            } else {
                _isSuccess.postValue(false)
            }
        }
    }

    fun getMember(dto: Member) {
        viewModelScope.launch {
            val response = repository.getMember(token,dto)
            if (response.isSuccessful) {
                _member.postValue(response.body()?.dto ?: Member())
            } else {
                _member.postValue(Member())
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