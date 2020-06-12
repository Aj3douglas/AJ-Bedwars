package org.aj3douglas.ajbedwars

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import org.aj3douglas.ajbedwars.core.GeneratorManager
import org.aj3douglas.ajbedwars.core.StoreEntityManager
import org.aj3douglas.ajbedwars.listeners.StoreEntityMenu
import org.aj3douglas.ajbedwars.utils.CommandManager
import org.aj3douglas.ajbedwars.utils.FileManager
import org.aj3douglas.ajbedwars.utils.ListenerManager
import org.aj3douglas.ajbedwars.utils.printStartupMessage
import org.bukkit.plugin.java.JavaPlugin

class AJBedwars : JavaPlugin() {
    private val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    private val fileManager = FileManager(this, gson)
    private val listenerManager = ListenerManager(this)

    private val generatorManager = GeneratorManager(this, fileManager)
    private val entityManager = StoreEntityManager(this, fileManager)

    private val cmdManager = CommandManager(this, generatorManager, entityManager)


    override fun onEnable() {
        fileManager.setupFiles()
        cmdManager.setupCommands()
        listenerManager.registerListeners(StoreEntityMenu())
        printStartupMessage(description.version, description.authors, description.description)
    }


}