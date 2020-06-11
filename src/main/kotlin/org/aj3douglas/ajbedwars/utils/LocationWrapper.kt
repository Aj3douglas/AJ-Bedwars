package org.aj3douglas.ajbedwars.utils

import org.bukkit.Location
import org.bukkit.World


data class LocationWrapper(val x:Int, val y:Int, val z:Int){
    fun toLocation(world: World): Location = Location(world, x.toDouble(), y.toDouble(), z.toDouble())
}