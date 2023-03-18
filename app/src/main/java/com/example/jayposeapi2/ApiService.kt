package com.example.jayposeapi2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// 2023-03-10 Accesses https://gist.githubusercontent.com/android-test-vaxcare/02cb5f20bf3398ca46884e6c8e18ce89/raw/462e69054eaef1ac92386c549f66324e4b89dbde/local-database.json

// https://gist.githubusercontent.com/jaykallen/8a1f6981d842fda91b2d1a68313b8a5d/raw/12b614184685777c3f3541b2642b2b83cf7a2464/gistfile1.txt

interface ApiService {
    @GET("jaykallen//{key1}//raw//{key2}//books.json")
    fun queryApi(@Path("key1") key1:String, @Path("key2") key2: String): Call<Books>
}