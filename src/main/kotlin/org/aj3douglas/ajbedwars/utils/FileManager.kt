package org.aj3douglas.ajbedwars.utils

import com.google.gson.Gson
import org.aj3douglas.ajbedwars.core.Generator
import org.aj3douglas.ajbedwars.core.StoreEntity
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File

class FileManager(private val plugin: Plugin, private val gson: Gson) {
    private val df = plugin.dataFolder
    private val teamsFile = df.resolve("teams.json")
    private val storeEntitiesFile = df.resolve("store_entities.json")
    private val generatorsFile = df.resolve("generators.json")
    val loadedMapsFile = df.resolve("data/loaded-maps.yml")
    val loadedMaps = YamlConfiguration.loadConfiguration(loadedMapsFile)
    fun setupFiles() {
        loadedMaps.set("loaded-maps", listOf<String>())
        loadedMaps.save(loadedMapsFile)
        plugin.saveResource("data/loadedmaps.yml", false)
        plugin.saveResource("generators.json", false)
        plugin.saveResource("teams.json", false)
        plugin.saveResource("store_entities.json", false)
        loadedMaps.getStringList("loaded-maps").forEach {
            Bukkit.unloadWorld(it, false)
            File(plugin.server.worldContainer.resolve(it).absolutePath).deleteRecursively()
        }
    }

    fun readGenerators(): List<Generator> = gson.fromJson(
            generatorsFile.readText(),
            generatorTypetoken
    )

    fun readEntities(): List<StoreEntity> = gson.fromJson(
            storeEntitiesFile.readText(),
            entityTypeToken
    )
}