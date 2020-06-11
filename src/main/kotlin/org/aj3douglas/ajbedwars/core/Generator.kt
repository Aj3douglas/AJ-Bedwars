package org.aj3douglas.ajbedwars.core

import com.google.gson.annotations.SerializedName
import org.aj3douglas.ajbedwars.utils.LocationWrapper
import org.bukkit.Material

data class Generator(
    val name:String,
    val item:Material,
    @SerializedName("display_name")
    val displayName:String,
    @SerializedName("seconds_to_tier_1")
    val secondsToTier1:Int,
    @SerializedName("seconds_to_tier_2")
    val secondsToTier2:Int,
    @SerializedName("seconds_to_tier_3")
    val secondsToTier3:Int,
    @SerializedName("seconds_to_tier_4")
    val secondsToTier4:Int,
    @SerializedName("tier_speed")
    val tierSpeeds:Map<Int,Int>,
    val locations:List<LocationWrapper>
)

