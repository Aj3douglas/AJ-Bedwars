package org.aj3douglas.ajbedwars

import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import me.mattstudios.mf.base.CommandManager
import org.aj3douglas.ajbedwars.commands.GoToWorld
import org.aj3douglas.ajbedwars.commands.Test
import org.aj3douglas.ajbedwars.core.GeneratorManager
import org.aj3douglas.ajbedwars.core.StoreEntityManager
import org.aj3douglas.ajbedwars.core.Teams
import org.aj3douglas.ajbedwars.listeners.StoreEntityMenu
import org.aj3douglas.ajbedwars.utils.colour
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.FileReader
import java.io.Reader
import java.nio.file.Path
import java.util.logging.Level
import kotlin.properties.Delegates

class AJBedwars:JavaPlugin() {
    val generatorManager = GeneratorManager(this)
    val entityManager = StoreEntityManager(this)

    private val gson  = GsonBuilder().create()
    val teamsFile = File("${this.dataFolder}/teams.json")
    val storeEntitiesFile = File("${this.dataFolder}/store_entities.json")
    val generatorsFile = File("${this.dataFolder}/generators.json")
    val loadedMapsFile = File("${this.dataFolder}/data/loadedmaps.yml")
    val loadedMaps = YamlConfiguration.loadConfiguration(loadedMapsFile)
    var holoGrams = false
    override fun onEnable() {
        server.pluginManager.registerEvents(StoreEntityMenu(), this)
        holoGrams = Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")
        setupFiles()
        loadedMaps.set("loaded-maps", listOf<String>())
        loadedMaps.save(loadedMapsFile)
        println("""
            &a----------<&bAJ Bedwars&a>----------
            &aVersion:&b ${this.description.version}
            &aAuthors: [&b${this.description.authors.joinToString(", ")}]
            &aDescription: &b${this.description.description}
            &a----------<&bAJ Bedwars&a>----------
        """.trimIndent().colour())
        setupCommands()
    }


    private fun setupFiles(){
        saveResource("data/loadedmaps.yml", false)
        saveResource("generators.json",false)
        saveResource("teams.json",false)
        saveResource("store_entities.json",false)
        loadedMaps.getStringList("loaded-maps").forEach{
            Bukkit.unloadWorld(it, false)
            File(this.server.worldContainer.resolve(it).absolutePath).deleteRecursively()
        }
    }
    private fun setupCommands(){
        val cm = CommandManager(this)
        cm.register(Test(this))
        cm.register(GoToWorld())

    }
}