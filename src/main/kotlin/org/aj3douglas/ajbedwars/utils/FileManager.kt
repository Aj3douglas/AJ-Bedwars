package org.aj3douglas.ajbedwars.utils

import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import org.aj3douglas.ajbedwars.AJBedwars
import org.aj3douglas.ajbedwars.core.Generator
import org.aj3douglas.ajbedwars.core.StoreEntity
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.FileReader

class FileManager( dataFolder:File, private val worldContainer:File, private val plugin: JavaPlugin, private val gson:Gson) {
    val teamsFile = File("$dataFolder/teams.json")
    val storeEntitiesFile = File("$dataFolder/store_entities.json")
    val generatorsFile = File("$dataFolder/generators.json")
    val loadedMapsFile = File("$dataFolder/data/loadedmaps.yml")
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
            File(worldContainer.resolve(it).absolutePath).deleteRecursively()
        }
    }

    fun readGenerators():List<Generator> = gson.fromJson(
            JsonReader(FileReader(generatorsFile)),
            Utils.generatorTypetoken
    )

    fun readEntities():List<StoreEntity> = gson.fromJson(
            JsonReader(FileReader(storeEntitiesFile)),
            Utils.entityTypeToken
    )
}