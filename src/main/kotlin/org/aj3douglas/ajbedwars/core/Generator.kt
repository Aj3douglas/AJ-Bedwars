package org.aj3douglas.ajbedwars.core

import com.google.gson.annotations.SerializedName
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World

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


data class LocationWrapper(val x:Int, val y:Int, val z:Int){
    fun toLocation(world:World):Location = Location(world, x.toDouble(), y.toDouble(), z.toDouble())
}