package com.myshop_application.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myshop_application.model.Order
import com.myshop_application.repository.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel(private val repository: OrderRepository) : ViewModel() {
    private val _orderId = MutableLiveData<Long>()
    val orderId: LiveData<Long> = _orderId

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun getOrderId(token:String) {
        viewModelScope.launch {
            val response = repository.getOrderId(token)
            if (response.isSuccessful) {
                _orderId.postValue(response.body())
            } else {
                _orderId.postValue(-1)
            }
        }
    }

    fun addOrder(token:String,dto: Order) {
        viewModelScope.launch {
            val response = repository.addOrders(token,dto)
            if (response.isSuccessful) {
                _isSuccess.postValue(true)
            } else {
                _isSuccess.postValue(false)
            }
        }
    }

    class ViewModelFactory(private val repository: OrderRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return OrderViewModel(repository) as T
        }
    }
}