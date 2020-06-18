package org.aj3douglas.ajbedwars.core

import org.aj3douglas.ajbedwars.utils.FileManager
import org.aj3douglas.ajbedwars.utils.toLocation
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.metadata.MetadataValue
import org.bukkit.plugin.java.JavaPlugin

class StoreEntityManager(private val fileManager: FileManager, private val javaPlugin: JavaPlugin) {

    private val entities = mutableListOf<Entity>()

    fun createEntities(world:World){
        val entitiesMap = fileManager.readEntities() ?: return
        entitiesMap.forEach{ (name, data) ->
            data.locations.forEach{location->
                val entity = world.spawnEntity(location.toLocation(world), data.entity.type) as LivingEntity
                entity.setMetadata("store-entity", FixedMetadataValue(javaPlugin, data.entity.menu))
                entity.isSilent = true
                entity.customName = name
                entity.isGlowing = data.entity.meta.glowing
                entity.setAI(false)
                entity.isCollidable = false
                entities.add(entity)
            }
        }
    }
    fun killEntities() = entities.forEach{ it.remove()}
}