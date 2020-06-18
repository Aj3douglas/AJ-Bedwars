package org.aj3douglas.ajbedwars.listeners

import org.aj3douglas.ajbedwars.utils.FileManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent

class StoreEntityMenu(private val fileManager: FileManager) : Listener {
    @EventHandler
    fun onEntityClick(event: PlayerInteractAtEntityEvent) {
        val entity = event.rightClicked
        val meta = entity.getMetadata("store-entity-menu")[0]
        val menuFile = fileManager.readMenu(meta.asString()) ?: return
    }

}