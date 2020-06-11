package org.aj3douglas.ajbedwars.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack

fun String.colour() = ChatColor.translateAlternateColorCodes('&', this)

fun String.debug() = Bukkit.broadcastMessage("&c(!) [DEBUG] (!)\n&b$this".colour())

fun ItemStack.setName(name:String):ItemStack{
    val meta = this.itemMeta ?: return this
    meta.setDisplayName(name)
    this.itemMeta = meta
    return this
}