package org.aj3douglas.ajbedwars.listeners

import org.aj3douglas.ajbedwars.utils.debug
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent

class StoreEntityMenu: Listener {
    @EventHandler
    fun onEntityClick(event:PlayerInteractAtEntityEvent){
        if(event.rightClicked.hasMetadata("store-entity")){
            "WOOT".debug()
        }
    }
}