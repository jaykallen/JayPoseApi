package com.example.jayposeapi2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {
    private var _bookList = MutableStateFlow<Books>(listOf())
    val bookList = _bookList.asStateFlow()
    var apiCode by mutableStateOf("000")
        private set

    fun initRetrofit(): ApiService {
        val BASE_URL = "https://gist.githubusercontent.com/"
        val gson: Gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(ApiService::class.java)
    }

    fun callApi() {
        val key1 = "8a1f6981d842fda91b2d1a68313b8a5d"
        val key2 = "12b614184685777c3f3541b2642b2b83cf7a2464"
        val apiService = initRetrofit()
        val apiCall = apiService.queryApi(key1, key2)
        println("Attempting URL: ${apiCall.request().url()}")
        apiCall.enqueue(object : retrofit2.Callback<Books> {
            override fun onFailure(call: Call<Books>, t: Throwable) {
                println("Failed retrofit call")
                apiCode = "666"
            }
            override fun onResponse(call: Call<Books>, response: retrofit2.Response<Books> ) {
                val apiData: Books? = response.body()
                val tempList = apiData?: listOf()
                println("call is ${response.code()}")
                if (tempList.isNotEmpty()) {
                    println("Retrieved ${tempList.size} items from JSON Data")
                    println("Got id ${tempList[0].id} from JSON Data")
                    println("Got title ${tempList[0].title} from JSON Data")
                    println("Got author ${tempList[0].author} from JSON Data")
                    println("Got status ${tempList[0].status} from JSON Data")
                }
                apiCode = response.code().toString()
                _bookList.value = apiData ?: listOf()
                println(apiData.toString())
            }
        })
    }
}