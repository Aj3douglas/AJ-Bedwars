package org.aj3douglas.ajbedwars.core

import com.google.common.reflect.TypeToken
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import org.aj3douglas.ajbedwars.AJBedwars
import org.aj3douglas.ajbedwars.utils.debug
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.metadata.MetadataValue
import java.io.FileReader

class StoreEntityManager(private val plugin:AJBedwars) {
    val gson = GsonBuilder().create()
    val entities = mutableListOf<Entity>()
    fun createEntities(world:World){
        readEntities().forEach{entity->
            if(entity.locations == null){
                "locatiosn ull".debug()
                return
            }
            entity.locations.forEach{location->
                val loc = location.toLocation(world)
                if(loc == null){
                    "loc null".debug()
                    return
                }
                if(entity.type == null){
                    "entity type null fuck".debug()
                    return
                }
                val entity = world.spawnEntity(loc, entity.type) as? LivingEntity
                if(entity == null){
                    "FUCK".debug()
                    return
                }
                entity.setMetadata("store-entity", FixedMetadataValue(plugin, true))
                entity.setGravity(false)
                entity.customName = entity.name
                entity.isCustomNameVisible = true
                entity.isSilent = true
                entity.setAI(false)
                entity.isInvulnerable = true
                entity.isCollidable = false
                entities.add(entity)
            }
        }
    }

    fun kill() = entities.forEach{ if(it.hasMetadata("store-entity")) it.remove() }

    private fun readEntities():List<StoreEntity> = gson.fromJson(
        JsonReader(FileReader(plugin.storeEntitiesFile)),
        object : TypeToken<List<StoreEntity>>() {}.type
    )
}