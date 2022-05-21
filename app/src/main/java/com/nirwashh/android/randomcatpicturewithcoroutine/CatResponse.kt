package com.nirwashh.android.randomcatpicturewithcoroutine

import com.google.gson.annotations.SerializedName

data class CatResponse(
    @SerializedName("file")
    val file: String
)
