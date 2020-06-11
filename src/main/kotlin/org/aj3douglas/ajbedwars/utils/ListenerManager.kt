package org.aj3douglas.ajbedwars.utils

import org.aj3douglas.ajbedwars.AJBedwars
import org.bukkit.event.Listener

class ListenerManager(private val plugin:AJBedwars) {
    fun registerListeners(vararg listener:Listener) = listener.forEach { plugin.server.pluginManager.registerEvents(it, plugin) }
}