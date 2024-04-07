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

class SignupViewModel(private val repository: MemberRepository) : ViewModel() {
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun signup(dto: Member) {
        viewModelScope.launch {
            val response = repository.addMember(dto)
            if (response.isSuccessful) {
                _isSuccess.postValue(true)
            } else {
                _isSuccess.postValue(false)
            }
        }
    }

    //acitivity에서 viewmodel을 호출할때파라미터를 가져오기 위함
    class ViewModelFactory(private val imageRepository: MemberRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SignupViewModel(imageRepository) as T
        }
    }
}