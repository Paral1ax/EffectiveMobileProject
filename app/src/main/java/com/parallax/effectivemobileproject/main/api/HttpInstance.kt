package com.parallax.effectivemobileproject.main.api

import com.parallax.effectivemobileproject.main.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.Request

object HttpInstance {
    private val okHttp = OkHttpClient()

    fun getHttpInstance(): OkHttpClient {
        return okHttp
    }
}