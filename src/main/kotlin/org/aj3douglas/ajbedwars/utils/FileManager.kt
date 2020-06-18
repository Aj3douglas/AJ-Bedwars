package org.aj3douglas.ajbedwars.utils

import com.sxtanna.korm.Korm
import org.aj3douglas.ajbedwars.core.data.GeneratorData
import org.aj3douglas.ajbedwars.core.data.MenuData
import org.aj3douglas.ajbedwars.core.data.StoreEntityData
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File


class FileManager(private val plugin: Plugin, private val korm: Korm) {
    private val df = plugin.dataFolder
    private val generatorsFile = df.resolve("generators.korm")
    private val entitiesFile = df.resolve("store_entities.korm")
    val loadedMapsFile = df.resolve("data/loaded-maps.yml")
    val loadedMaps = YamlConfiguration.loadConfiguration(loadedMapsFile)
    fun setupFiles() {
        plugin.config.options().copyDefaults(true)
        plugin.saveDefaultConfig()
        loadedMaps.set("loaded-maps", listOf<String>())
        loadedMaps.save(loadedMapsFile)
        plugin.saveResource("data/loadedmaps.yml", false)
        plugin.saveResource("generators.korm", false)
        plugin.saveResource("store_entities.korm", false)
        plugin.saveResource("menus/example_menu.korm", false)
        loadedMaps.getStringList("loaded-maps").forEach {
            Bukkit.unloadWorld(it, false)
            File(plugin.server.worldContainer.resolve(it).absolutePath).deleteRecursively()
        }
    }

    fun readEntities(): Map<String, StoreEntityData>? = korm.pull(entitiesFile).toHash()

    fun readMenu(menuFileName: String): Map<Int, MenuData>? = korm.pull(df.resolve("menus/$menuFileName.korm")).toHash()

    fun readGenerators(): Map<String, GeneratorData>? = korm.pull(generatorsFile).toHash()

}