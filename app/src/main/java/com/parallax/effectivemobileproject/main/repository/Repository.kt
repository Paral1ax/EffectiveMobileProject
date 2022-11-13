package com.parallax.effectivemobileproject.main.repository

import com.parallax.effectivemobileproject.main.api.RetrofitInstance
import com.parallax.effectivemobileproject.main.model.HomeStoreItem
import com.parallax.effectivemobileproject.main.model.MainApiModel
import retrofit2.Response

class Repository {

    suspend fun getHotSales(): Response<MainApiModel> {
        return RetrofitInstance.api.getHotSales()
    }
}