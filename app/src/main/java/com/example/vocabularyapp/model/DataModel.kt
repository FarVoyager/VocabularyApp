package com.example.vocabularyapp.model

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("text") val text: String?,
    @SerializedName("meanings") val meanings: List<Meanings>?
)

data class Meanings(
    @SerializedName("translation") val translation: Translation?,
    @SerializedName("imageUrl") val imageUrl: String?
)

data class Translation(@SerializedName("text") val translation: String?)
