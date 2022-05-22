package com.nirwashh.android.randomcatpicturewithcoroutine


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getRetrofit(): Retrofit = Retrofit.Builder()

        //.baseUrl("https://aws.random.cat/").build()
        .baseUrl("https://picsum.photos/200/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}
