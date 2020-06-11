package org.aj3douglas.ajbedwars.utils

import me.mattstudios.mf.base.CommandManager
import org.aj3douglas.ajbedwars.AJBedwars
import org.aj3douglas.ajbedwars.commands.GoToWorld
import org.aj3douglas.ajbedwars.commands.Test
import org.aj3douglas.ajbedwars.core.GeneratorManager
import org.aj3douglas.ajbedwars.core.StoreEntityManager
import org.bukkit.plugin.java.JavaPlugin

class CommandManager(private val plugin:JavaPlugin, private val generatorManager: GeneratorManager, private val entityManager: StoreEntityManager) {
    fun setupCommands(){
        val cm = CommandManager(plugin)
        cm.register(Test(generatorManager, entityManager))
        cm.register(GoToWorld())

    }
}