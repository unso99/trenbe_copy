package com.myshop_application.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myshop_application.model.OrderDetail
import com.myshop_application.repository.OrderDetailRepository
import kotlinx.coroutines.launch

class OrderDetailViewModel(private val repository: OrderDetailRepository) : ViewModel() {
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun addOrderDetail(dto: OrderDetail) {
        viewModelScope.launch {
            val response = repository.addOrderDetail(dto)
            if (response.isSuccessful) {
                _isSuccess.postValue(true)
            } else {
                _isSuccess.postValue(false)
            }
        }
    }

    class ViewModelFactory(private val repository: OrderDetailRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return OrderDetailViewModel(repository) as T
        }
    }
}