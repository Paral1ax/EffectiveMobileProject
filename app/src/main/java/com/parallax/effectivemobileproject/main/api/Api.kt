package com.parallax.effectivemobileproject.main.api

import com.parallax.effectivemobileproject.main.model.HomeStoreItem
import com.parallax.effectivemobileproject.main.model.MainApiModel
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getHotSales() : Response<MainApiModel>
}