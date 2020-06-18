package org.aj3douglas.ajbedwars

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.sxtanna.korm.Korm
import org.aj3douglas.ajbedwars.core.GeneratorManager
import org.aj3douglas.ajbedwars.core.MenuManager
import org.aj3douglas.ajbedwars.core.StoreEntityManager
import org.aj3douglas.ajbedwars.listeners.StoreEntityMenu
import org.aj3douglas.ajbedwars.utils.*
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.plugin.java.JavaPlugin

class AJBedwars : JavaPlugin() {
    companion object{
        var debug:Boolean = false
    }

    private val korm = Korm()
    private val fileManager = FileManager(this, korm)
    private val listenerManager = ListenerManager(this)

    private val generatorManager = GeneratorManager(this, fileManager)
    private val storeEntityManager = StoreEntityManager(fileManager, this)
    private val menuManager = MenuManager(fileManager, this)

    private val cmdManager = CommandManager(this, generatorManager, storeEntityManager,menuManager)


    override fun onEnable() {
        debug = config.getBoolean("debug")
        fileManager.setupFiles()
        cmdManager.setupCommands()
        listenerManager.registerListeners(StoreEntityMenu(fileManager))
        Bukkit.getConsoleSender().sendMessage("""
            &a----------<&bAJ Bedwars&a>----------
            &aVersion:&b ${description.version}
            &aAuthors: [&b${description.authors.joinToString(", ")}]
            &aDescription: &b${description.description}
            &a----------<&bAJ Bedwars&a>----------
        """.trimIndent().colour())
    }


}