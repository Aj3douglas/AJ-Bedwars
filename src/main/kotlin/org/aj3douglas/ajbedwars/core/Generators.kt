package org.aj3douglas.ajbedwars.core

import com.google.gson.annotations.SerializedName

data class Generators(
    @SerializedName("diamond_generators")
    val diamondGenerators: DiamondGenerators,
    @SerializedName("emerald_generators")
    val emeraldGenerators: EmeraldGenerators
)

data class DiamondGenerators(
    val minutesToTier1:Int,
    val minutesToTier2:Int,
    val minutesToTier3:Int,
    val minutesToTier4:Int,
    val tier1Speed:Int,
    val tier2Speed:Int,
    val tier3Speed:Int,
    val tier4Speed:Int,
    val locations:List<LocationWrapper>
)
data class EmeraldGenerators(
    val minutesToTier1:Int,
    val minutesToTier2:Int,
    val minutesToTier3:Int,
    val minutesToTier4:Int,
    val tier1Speed:Int,
    val tier2Speed:Int,
    val tier3Speed:Int,
    val tier4Speed:Int,
    val locations:List<LocationWrapper>
)