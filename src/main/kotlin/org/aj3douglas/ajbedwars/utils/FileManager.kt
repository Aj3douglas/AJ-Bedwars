package org.aj3douglas.ajbedwars.utils

import org.aj3douglas.ajbedwars.AJBedwars
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class FileManager(private val plugin:AJBedwars) {
    val teamsFile = File("${plugin.dataFolder}/teams.json")
    val storeEntitiesFile = File("${plugin.dataFolder}/store_entities.json")
    val generatorsFile = File("${plugin.dataFolder}/generators.json")
    val loadedMapsFile = File("${plugin.dataFolder}/data/loadedmaps.yml")
    val loadedMaps = YamlConfiguration.loadConfiguration(loadedMapsFile)
    fun setupFiles(){
        loadedMaps.set("loaded-maps", listOf<String>())
        loadedMaps.save(loadedMapsFile)
        plugin.saveResource("data/loadedmaps.yml", false)
        plugin.saveResource("generators.json",false)
        plugin.saveResource("teams.json",false)
        plugin.saveResource("store_entities.json",false)
        loadedMaps.getStringList("loaded-maps").forEach{
            Bukkit.unloadWorld(it, false)
            File(plugin.server.worldContainer.resolve(it).absolutePath).deleteRecursively()
        }
    }
}