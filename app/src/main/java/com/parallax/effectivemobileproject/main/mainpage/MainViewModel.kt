package com.parallax.effectivemobileproject.main.mainpage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parallax.effectivemobileproject.main.model.HomeStoreItem
import com.parallax.effectivemobileproject.main.model.MainApiModel
import com.parallax.effectivemobileproject.main.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val mainModel = MutableLiveData<Response<MainApiModel>>()

    fun getHotSales() {
        viewModelScope.launch {
            val response = repository.getHotSales()
            mainModel.value = response
        }
    }

}