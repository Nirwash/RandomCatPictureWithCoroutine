package com.nirwashh.android.randomcatpicturewithcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nirwashh.android.randomcatpicturewithcoroutine.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import retrofit2.await

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var b: ActivityMainBinding
    private val service by lazy { RetrofitClient.getRetrofit().create(CatService::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch {
            val url = loadUrl()
            Picasso.get().load(url).into(b.imgCat)
        }
    }

    suspend fun loadUrl() = withContext(Dispatchers.Default) {
        service.getCat().await().file
    }
}