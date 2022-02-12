package com.utad.iplanet.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PlanetItem(
    @SerializedName("id") val id : String,
    @SerializedName("planetName") val planetName : String,
    @SerializedName("planetDistanceMio") val planetDistanceMio : Int,
    @SerializedName("planetEquatorialRadius") val planetEquatorialRadius : Int,
    @SerializedName("planetRotationPeriod") val planetRotationPeriod : Int,
    @SerializedName("planetMassKg") val planetMassKg : String,
    @SerializedName("planetDensity") val planetDensity : Int,
    @SerializedName("category") val category : String,
    @SerializedName("planetUrlImage") val planetUrlImage : String
)
