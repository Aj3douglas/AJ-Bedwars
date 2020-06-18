package org.aj3douglas.ajbedwars.core


import org.aj3douglas.ajbedwars.utils.FileManager
import org.aj3douglas.ajbedwars.utils.debug
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.WorldCreator
import java.io.File

class GameManager(
    private val dataFolder: File,
    private val worldContainer: File,
    private val fileManager: FileManager
) {
    var gameInProgress = false
    fun prepareWorld(): World? {
        val tempworld = dataFolder.resolve("worlds").listFiles().randomOrNull() ?: return null
        tempworld.copyRecursively(File(worldContainer, tempworld.name), true)
        val world = Bukkit.createWorld(WorldCreator(tempworld.name)) ?: return null
        "World name: ${world.name}".debug()
        val mapsList = fileManager.loadedMaps.getStringList("loaded-maps")
        mapsList.add(world.name)
        fileManager.loadedMaps.set("loaded-maps", mapsList)
        fileManager.loadedMaps.save(fileManager.loadedMapsFile)
        gameInProgress = true
        return world
    }
}