package org.aj3douglas.ajbedwars.core

import com.google.gson.annotations.SerializedName

data class Generators(
    @SerializedName("diamond_generators")
    val diamondGenerators: DiamondGenerators,
    @SerializedName("emerald_generators")
    val emeraldGenerators: EmeraldGenerators
)