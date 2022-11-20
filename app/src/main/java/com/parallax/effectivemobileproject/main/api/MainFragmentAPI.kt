package com.parallax.effectivemobileproject.main.api

import androidx.lifecycle.MutableLiveData
import com.parallax.effectivemobileproject.main.model.main.HomeStoreItem

interface MainFragmentAPI {

    suspend fun getMainApiModel(mutableLiveData: MutableLiveData<MutableList<HomeStoreItem>>)
}