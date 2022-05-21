package com.nirwashh.android.randomcatpicturewithcoroutine

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import java.lang.IllegalStateException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface CatService {

    @GET(value = "meow")
    fun getCat(): Call<CatResponse>
}

suspend fun <T> Call<T>.await() = suspendCoroutine<T> {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                it.resume(response.body()!!)
            } else {
                it.resumeWithException(IllegalStateException("wrong"))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            it.resumeWithException(t)
        }
    })
}