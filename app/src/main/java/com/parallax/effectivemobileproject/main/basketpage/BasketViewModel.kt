package com.parallax.effectivemobileproject.main.basketpage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.parallax.effectivemobileproject.main.MainActivity
import com.parallax.effectivemobileproject.main.api.HttpInstance
import com.parallax.effectivemobileproject.main.model.cart.Basket
import com.parallax.effectivemobileproject.main.model.item.ItemCharacteristics
import com.parallax.effectivemobileproject.main.utils.Constants
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class BasketViewModel : ViewModel() {

    val userBasket: MutableLiveData<Basket> = MutableLiveData()

    fun getUserBasketItems(activity: MainActivity) {
        viewModelScope.launch {
            val request = Request.Builder().url(Constants.BASKET_API_URL).build()
            val call = HttpInstance.getHttpInstance().newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("API", "Ошибка в запросе фрагмента корзины апи $e")
                }
                override fun onResponse(call: Call, response: Response) {
                    Log.d("API", "Запрос к корзине апи был совершен успешно, продолжаем обработку строки")
                    response.use {
                        if (!response.isSuccessful) {
                            Log.d("API", "Http error on cart request!!!")
                        }
                        else {
                            Log.d("API","Происходит обработка ответа и конвертация в json")
                            val stringResponse = response.body?.string()
                            val gson = Gson()
                            val responseItem = gson.fromJson(stringResponse.toString(), Basket::class.java)
                            activity.runOnUiThread {
                                userBasket.value = responseItem
                            }
                            Log.d("API","Конвертация прошла успешно!")
                        }
                    }
                }

            })
        }
    }
}