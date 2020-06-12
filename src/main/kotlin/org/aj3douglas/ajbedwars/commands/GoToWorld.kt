package org.aj3douglas.ajbedwars.commands

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import org.aj3douglas.ajbedwars.utils.debug
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

@Command("gotoworld")
class GoToWorld : CommandBase() {
    @Default
    fun doStuff(player: Player, world: String) {
        val loc = Location(Bukkit.getWorld(world), player.location.x, player.location.y, player.location.z)
        player.teleport(loc)
        "Teleported to ${loc.world}".debug()
    }
}