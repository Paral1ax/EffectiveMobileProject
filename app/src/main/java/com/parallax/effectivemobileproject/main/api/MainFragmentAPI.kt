package com.parallax.effectivemobileproject.main.api

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.parallax.effectivemobileproject.main.model.HomeStoreItem
import com.parallax.effectivemobileproject.main.model.MainApiModel

interface MainFragmentAPI {

    suspend fun getMainApiModel(mutableLiveData: MutableLiveData<MutableList<HomeStoreItem>>)
}