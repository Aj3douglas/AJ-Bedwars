package org.aj3douglas.ajbedwars.core.data

import org.bukkit.entity.EntityType

class StoreEntityData(val entity:Entity, val locations:List<String>){

    class Entity(val type:EntityType, val menu:String, val meta:Meta){

        class Meta(val glowing:Boolean)

    }

}
