package org.aj3douglas.ajbedwars.commands

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.base.CommandBase
import org.aj3douglas.ajbedwars.AJBedwars
import org.aj3douglas.ajbedwars.core.GameManager
import org.aj3douglas.ajbedwars.core.GeneratorManager
import org.aj3douglas.ajbedwars.utils.debug
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

@Command("test")
class Test(private val plugin:AJBedwars):CommandBase() {
    @Default
    fun test(player:Player){
        plugin.generatorManager.createGenerators(player.world)
    }
    @SubCommand("kill")
    fun kill(player:Player){
        plugin.generatorManager.killGenerators()
    }
}