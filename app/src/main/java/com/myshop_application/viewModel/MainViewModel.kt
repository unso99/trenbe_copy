package com.myshop_application.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myshop_application.model.Common
import com.myshop_application.model.Product
import com.myshop_application.repository.CommonRepository
import com.myshop_application.repository.ProductRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val productRepository: ProductRepository,
    private val commonRepository: CommonRepository
) : ViewModel() {
    private val _list = MutableLiveData<List<Product>>()
    val list: LiveData<List<Product>> = _list
    private val _commonList = MutableLiveData<List<Common>>()
    val commonList: LiveData<List<Common>> = _commonList

    fun getList(token : String, body: Product) {
        viewModelScope.launch {
            val response = productRepository.getList(token,body)
            if (response.isSuccessful) {
                val result = response.body()?.list ?: emptyList()
                _list.postValue(result!!)
            } else {
                _list.postValue(emptyList())
            }
        }
    }

    fun getCommonList(token:String,body: Common) {
        viewModelScope.launch {
            val response = commonRepository.getCommonList(token,body)
            if (response.isSuccessful) {
                val result = response.body()?.list ?: emptyList()
                _commonList.postValue(result!!)
            } else {
                _commonList.postValue(emptyList())
            }
        }
    }

    fun getSearchList(token:String,keyword : String) {
        viewModelScope.launch {
            val response = productRepository.getSearchList(token,keyword)
            if (response.isSuccessful) {
                val result = response.body()?.list ?: emptyList()
                _list.postValue(result!!)
            } else {
                _list.postValue(emptyList())
            }
        }
    }

    class ViewModelFactory(
        private val productRepository: ProductRepository,
        private val commonRepository: CommonRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(productRepository, commonRepository) as T
        }
    }
}