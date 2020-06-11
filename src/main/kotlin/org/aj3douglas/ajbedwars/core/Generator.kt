package org.aj3douglas.ajbedwars.core

import com.google.gson.annotations.SerializedName
import org.aj3douglas.ajbedwars.utils.LocationWrapper
import org.bukkit.Material

data class Generator(
    val name:String,
    val item:Material,
    val displayName:String,
    val secondsToTier1:Int,
    val secondsToTier2:Int,
    val secondsToTier3:Int,
    val secondsToTier4:Int,
    val tierSpeed:Map<Int,Int>,
    val locations:List<LocationWrapper>
)

