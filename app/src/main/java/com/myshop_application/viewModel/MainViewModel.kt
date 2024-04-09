package com.myshop_application.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myshop_application.model.Product
import com.myshop_application.repository.ProductRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _list = MutableLiveData<List<Product>>()
    val list: LiveData<List<Product>> = _list

    fun getList() {
        viewModelScope.launch {
            val response = repository.getList()
            if (response.isSuccessful) {
                val result = response.body()?.list ?: emptyList()
                _list.postValue(result!!)
            } else {
                _list.postValue(emptyList())
            }
        }
    }

    class ViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }
    }
}