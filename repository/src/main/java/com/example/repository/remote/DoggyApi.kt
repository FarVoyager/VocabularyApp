package com.example.repository.remote

import com.example.model.DoggyResponse
import retrofit2.http.GET

interface DoggyApi {
    @GET("random")
    suspend fun getRandomDog(): DoggyResponse
}