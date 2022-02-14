package com.utad.iplanet.model


import android.media.Image
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetService {
    @GET("/getallplanets/")
    fun getAllPlanets(): Call<List<PlanetItem>>

    @GET("/getplanetbycategory/{category}")
    fun getItemsByCategory(@Path("category") category: String): Call<List<PlanetItem>>





}