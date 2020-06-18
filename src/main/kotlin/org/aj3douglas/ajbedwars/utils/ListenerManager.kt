package org.aj3douglas.ajbedwars.utils

import org.aj3douglas.ajbedwars.AJBedwars
import org.bukkit.Bukkit
import org.bukkit.event.Listener

class ListenerManager(private val plugin: AJBedwars) {
    fun registerListeners(vararg listener: Listener) =
        listener.forEach { Bukkit.getPluginManager().registerEvents(it, plugin) }
}