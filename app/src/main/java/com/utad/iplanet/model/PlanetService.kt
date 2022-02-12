package com.utad.iplanet.model

import retrofit2.Call
import retrofit2.http.GET

interface PlanetService {
    @GET("/getallplanets/")
    fun getAllPlanets(): Call<List<PlanetItem>>
}