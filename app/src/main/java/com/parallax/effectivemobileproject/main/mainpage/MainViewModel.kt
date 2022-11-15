package com.parallax.effectivemobileproject.main.mainpage

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.parallax.effectivemobileproject.main.model.BestSellerItem
import com.parallax.effectivemobileproject.main.model.HomeStoreItem
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    fun getMainFragmentLiveData(context: Context) {
        viewModelScope.launch {
        }
    }

     companion object {
         val hotSales = MutableLiveData<List<HomeStoreItem>>()
         val bestSellers = MutableLiveData<List<BestSellerItem>>()
     }

}