package com.utad.iplanet.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PlanetService {
    @GET("/getallplanets/")
    fun getAllPlanets(): Call<List<PlanetItem>>

    @GET("/getplanetbycategory/Planet")
    fun getPlanetByCategoryPlanets(): Call<List<PlanetItem>>

    @GET("/getplanetbycategory/Star")
    fun getPlanetByCategoryStars(): Call<List<PlanetItem>>

    @GET("/getplanetbycategory/Moon")
    fun getPlanetByCategoryMoons(): Call<List<PlanetItem>>

    @GET
    fun getPlanetById(@Url url: String): Call<PlanetItem>



}