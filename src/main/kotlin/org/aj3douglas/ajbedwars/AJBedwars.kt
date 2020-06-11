package org.aj3douglas.ajbedwars

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import org.aj3douglas.ajbedwars.commands.GoToWorld
import org.aj3douglas.ajbedwars.commands.Test
import org.aj3douglas.ajbedwars.core.GeneratorManager
import org.aj3douglas.ajbedwars.core.StoreEntityManager
import org.aj3douglas.ajbedwars.listeners.StoreEntityMenu
import org.aj3douglas.ajbedwars.utils.*
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
    private val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    private val fileManager = FileManager(dataFolder, server.worldContainer, this, gson)
    val generatorManager = GeneratorManager(this, fileManager)
    val entityManager = StoreEntityManager(this, fileManager)
    private val cmdManager = CommandManager(this, generatorManager, entityManager)
    private val listenerManager = ListenerManager(this)


    override fun onEnable() {
        fileManager.setupFiles()
        cmdManager.setupCommands()
        listenerManager.registerListeners(StoreEntityMenu())
        Utils.holograms = Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")
        Utils.printStartupMessage(description.version, description.authors, description.description)
    }


}