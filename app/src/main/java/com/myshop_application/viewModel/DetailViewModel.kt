package com.myshop_application.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myshop_application.model.Cart
import com.myshop_application.repository.CartRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: CartRepository) : ViewModel() {
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _list = MutableLiveData<List<Cart>>()
    val list: LiveData<List<Cart>> = _list

    var price = 0L

    fun addCart(token:String,dto: Cart) {
        viewModelScope.launch {
            val response = repository.addCart(token,dto)
            if (response.isSuccessful) {
                _isSuccess.postValue(true)
            } else {
                _isSuccess.postValue(false)
            }
        }
    }

    fun getCarts(token:String,dto: Cart) {
        viewModelScope.launch {
            val response = repository.getCarts(token,dto)
            if (response.isSuccessful) {
                _list.postValue(response.body()?.list ?: emptyList())
                price = 0
                response.body()?.list!!.forEach {
                    price += it.product_price!!
                }
            } else {
                _list.postValue(emptyList())
            }
        }
    }

    fun deleteCart(token:String,dto: Cart) {
        viewModelScope.launch {
            val response = repository.deleteCart(token,dto);
            if (response.isSuccessful) {
                _list.postValue(response.body()?.list ?: emptyList())
                price = 0
                response.body()?.list!!.forEach {
                    price += it.product_price!!
                }
            } else {
                _list.postValue(emptyList())
            }
        }
    }

    class ViewModelFactory(private val repository: CartRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailViewModel(repository) as T
        }
    }
}