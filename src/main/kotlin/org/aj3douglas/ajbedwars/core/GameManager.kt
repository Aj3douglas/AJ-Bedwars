package org.aj3douglas.ajbedwars.core


import org.aj3douglas.ajbedwars.AJBedwars
import org.aj3douglas.ajbedwars.utils.debug
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import java.io.File

class GameManager(private val plugin:AJBedwars) {
    var gameInProgress = false
    fun prepareWorld():World?{
        val tempworld = plugin.dataFolder.resolve("worlds").listFiles().randomOrNull() ?: return null
        tempworld.copyRecursively(File(plugin.server.worldContainer, tempworld.name), true)
        val world = Bukkit.createWorld(WorldCreator(tempworld.name)) ?: return null
        "World name: ${world.name}".debug()
        val mapsList = plugin.loadedMaps.getStringList("loaded-maps")
        mapsList.add(world.name)
        plugin.loadedMaps.set("loaded-maps", mapsList)
        plugin.loadedMaps.save(plugin.loadedMapsFile)
        gameInProgress = true
        return world
    }
}