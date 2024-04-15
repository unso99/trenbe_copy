package com.myshop_application.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.myshop_application.model.Common
import com.myshop_application.repository.CommonRepository
import kotlinx.coroutines.launch

class CommonViewModel(private val repository: CommonRepository) : ViewModel() {
    private val _list = MutableLiveData<List<Common>>()
    val list: LiveData<List<Common>> = _list

    fun getCategory(p_code_id: Int) {
        viewModelScope.launch {
            val common = Common(p_code_id = p_code_id)
            val response = repository.getCommonList(common)
            if (response.isSuccessful) {
                _list.postValue(response.body()?.list ?: emptyList())
            } else {
                _list.postValue(emptyList())
            }
        }
    }

    class ViewModelFactory(private val repository: CommonRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CommonViewModel(repository) as T
        }
    }
}