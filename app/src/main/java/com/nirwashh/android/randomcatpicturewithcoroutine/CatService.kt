package com.nirwashh.android.randomcatpicturewithcoroutine

import kotlinx.coroutines.suspendCancellableCoroutine
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

suspend fun <T : Result<*>> Call<T>.await() = suspendCancellableCoroutine<T> { continuation ->
    continuation.invokeOnCancellation { cancel() }
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                continuation.resumeWith(Result.success(response.body()!!))
            } else {
                continuation.resumeWith(Result.failure(IllegalStateException("wrong")))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            continuation.resumeWith(Result.failure(t))
        }
    })
}