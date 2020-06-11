package org.aj3douglas.ajbedwars

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import org.aj3douglas.ajbedwars.commands.GoToWorld
import org.aj3douglas.ajbedwars.commands.Test
import org.aj3douglas.ajbedwars.core.GeneratorManager
import org.aj3douglas.ajbedwars.core.StoreEntityManager
import org.aj3douglas.ajbedwars.core.Teams
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
    val generatorManager = GeneratorManager(this)
    val entityManager = StoreEntityManager(this)
    val fileManager = FileManager(this)
    val cmdManager = CommandManager(this)
    val listenerManager = ListenerManager(this)

    val gsonBuilder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    override fun onEnable() {
        fileManager.setupFiles()
        cmdManager.setupCommands()
        listenerManager.registerListeners(StoreEntityMenu())
        Utils.holograms = Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")
        Utils.printStartupMessage(description.version, description.authors, description.description)
    }


}