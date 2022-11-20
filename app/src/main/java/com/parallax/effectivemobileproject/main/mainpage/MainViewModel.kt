package com.parallax.effectivemobileproject.main.mainpage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.MainActivity
import com.parallax.effectivemobileproject.main.api.HttpInstance
import com.parallax.effectivemobileproject.main.model.main.BestSellerItem
import com.parallax.effectivemobileproject.main.model.main.CategoryItem
import com.parallax.effectivemobileproject.main.model.main.HomeStoreItem
import com.parallax.effectivemobileproject.main.utils.Constants
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class MainViewModel() : ViewModel() {

    val hotSalesData: MutableLiveData<MutableList<HomeStoreItem>> = MutableLiveData()
    val bestSellersData: MutableLiveData<MutableList<BestSellerItem>> = MutableLiveData()

    fun getHotSales(activity: MainActivity) {
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
                            Log.d("API","Происходит обработка ответа и конвертация в json")
                            val stringResponse = response.body?.string()
                            val mainJson = JSONObject(stringResponse)
                            val hotSalesJson = mainJson.getJSONArray("home_store")
                            val bestSellersJson = mainJson.getJSONArray("best_seller")
                            val gson = Gson()
                            Log.d("API","Конвертация прошла успешно!")
                            val hotSales = gson.fromJson(hotSalesJson.toString(), Array<HomeStoreItem>::class.java).toMutableList()
                            val bestSellers = gson.fromJson(bestSellersJson.toString(), Array<BestSellerItem>::class.java).toMutableList()
                            activity.runOnUiThread {
                                hotSalesData.value = hotSales
                                bestSellersData.value = bestSellers
                            }
                        }
                    }
                }

            })
        }
    }

    fun setCategories(): MutableList<CategoryItem> {
        return mutableListOf(
            CategoryItem(R.drawable.ic_phone_category),
            CategoryItem(R.drawable.ic_computer_category),
            CategoryItem(R.drawable.ic_cardio_category),
            CategoryItem(R.drawable.ic_books_category),
        )
    }

}