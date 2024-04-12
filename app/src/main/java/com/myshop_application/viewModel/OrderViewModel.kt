package com.myshop_application.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myshop_application.repository.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel(private val repository: OrderRepository) : ViewModel() {
    private val _orderId = MutableLiveData<Int>()
    val orderId: LiveData<Int> = _orderId

    fun getOrderId() {
        viewModelScope.launch {
            val response = repository.getOrderId()
            if (response.isSuccessful) {
                _orderId.postValue(response.body())
            } else {
                _orderId.postValue(-1)
            }
        }
    }

    class ViewModelFactory(private val repository: OrderRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return OrderViewModel(repository) as T
        }
    }
}