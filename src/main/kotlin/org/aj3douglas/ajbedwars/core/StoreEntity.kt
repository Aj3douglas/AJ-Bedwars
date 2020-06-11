package org.aj3douglas.ajbedwars.core

import org.aj3douglas.ajbedwars.utils.LocationWrapper
import org.bukkit.entity.EntityType

data class StoreEntity(
    val name:String,
    val type: EntityType,
    val locations:List<LocationWrapper>
)