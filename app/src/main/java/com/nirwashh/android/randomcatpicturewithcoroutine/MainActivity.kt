package com.nirwashh.android.randomcatpicturewithcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nirwashh.android.randomcatpicturewithcoroutine.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private val service by lazy { RetrofitClient.getRetrofit().create(CatService::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            Picasso.get().load(service.getCat().await().file).into(b.imgCat)
        }
    }
}