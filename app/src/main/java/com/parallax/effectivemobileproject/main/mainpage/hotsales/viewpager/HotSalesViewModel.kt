package com.parallax.effectivemobileproject.main.mainpage.hotsales.viewpager

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.parallax.effectivemobileproject.main.api.HttpInstance
import com.parallax.effectivemobileproject.main.model.HomeStoreItem
import com.parallax.effectivemobileproject.main.utils.Constants
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class HotSalesViewModel() : ViewModel() {

    val responseData: MutableLiveData<MutableList<HomeStoreItem>> = MutableLiveData()

    fun getHotSales() {
        viewModelScope.launch {
            val request = Request.Builder().url(Constants.MAIN_FRAGMENT_API_URL).build()
            val call = HttpInstance.getHttpInstance().newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("API", "Ошибка в мэйн апи $e")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.d("API", "Запрос к мэйн апи был совершен успешно, продолжаем обработку строки")
                    response.use {
                        if (!response.isSuccessful) {
                            Log.d("API", "Http error on main request!!!")
                        }
                        else {
                            val stringResponse = response.body?.string()
                            val mainJson = JSONObject(stringResponse)
                            val hotSalesJson = mainJson.getJSONArray("home_store")
                            val gson = Gson()
                            responseData.value = gson.fromJson(hotSalesJson.toString(), Array<HomeStoreItem>::class.java).toMutableList()
                        }
                    }
                }

            })
        }
    }

}