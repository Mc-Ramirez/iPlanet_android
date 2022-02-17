package com.utad.iplanet.model

import com.google.gson.annotations.SerializedName

data class PlanetBody(
    @SerializedName("planetName") val planetName : String,
    @SerializedName("planetDistanceMio") val planetDistanceMio : String,
    @SerializedName("planetEquatorialRadius") val planetEquatorialRadius : String,
    @SerializedName("planetRotationPeriod") val planetRotationPeriod : String,
    @SerializedName("planetMassKg") val planetMassKg : String,
    @SerializedName("planetDensity") val planetDensity : String,
    @SerializedName("category") val category : String,
    @SerializedName("planetUrlImage") val planetUrlImage : String
)
