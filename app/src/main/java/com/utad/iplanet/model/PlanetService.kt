package com.utad.iplanet.model


import retrofit2.Call
import retrofit2.http.*

interface PlanetService {
    @GET("/getallplanets/")
    fun getAllPlanets(): Call<List<PlanetItem>>

    @GET("/getplanetbycategory/{category}")
    fun getItemsByCategory(@Path("category") category: String): Call<List<PlanetItem>>

    @GET("/getplanetbyname/{planetName}")
    fun getItemByName(@Path("planetName") planetName: String): Call<PlanetItem>

    @GET("/getplanetbyid/{id}")
    fun getItemById(@Path("id") id: String): Call<PlanetItem>

    @POST("/addplanet")
    fun addNewPlanet(@Body planetBody: PlanetBody): Call<PlanetItem>

    @PUT("/editplanet/{id}")
    fun editPlanet(@Path("id") id:String, @Body planetBody: PlanetBody): Call<PlanetItem>

    @DELETE("/deletebyid/{id}")
    fun deletePlanetById(@Path("id") id: String): Call<String>
}