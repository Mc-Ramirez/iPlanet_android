package com.utad.iplanet.model


import android.media.Image
import retrofit2.Call
import retrofit2.http.*

interface PlanetService {
    @GET("/getallplanets/")
    fun getAllPlanets(): Call<List<PlanetItem>>

    @GET("/getplanetbycategory/{category}")
     fun getItemsByCategory(@Path("category") category: String): Call<List<PlanetItem>>

    @GET("/getplanetbyname/{planetName}")
     fun getItemByName(@Path("planetName") planetName: String): Call<List<PlanetItem>>

    @GET("/getplanetbyid/{id}")
     fun getItemById(@Path("id") id: String): Call<PlanetItem>

     @POST("/addplanet")
     fun addNewPlanet(@Body planetBody: PlanetBody): Call<String>

     @DELETE("/detelebyid/{id}")
     fun deletePlanetById(@Path("id") id:String): Call<String>
}