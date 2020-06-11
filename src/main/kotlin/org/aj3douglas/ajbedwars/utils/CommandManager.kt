package org.aj3douglas.ajbedwars.utils

import me.mattstudios.mf.base.CommandManager
import org.aj3douglas.ajbedwars.AJBedwars
import org.aj3douglas.ajbedwars.commands.GoToWorld
import org.aj3douglas.ajbedwars.commands.Test

class CommandManager(private val plugin:AJBedwars) {
    fun setupCommands(){
        val cm = CommandManager(plugin)
        cm.register(Test(plugin))
        cm.register(GoToWorld())

    }
}