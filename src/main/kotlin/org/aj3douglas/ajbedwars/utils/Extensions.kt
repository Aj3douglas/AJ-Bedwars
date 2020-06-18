package org.aj3douglas.ajbedwars.utils

import com.google.common.reflect.TypeToken
import org.bukkit.inventory.ItemStack
import com.google.gson.Gson
import org.aj3douglas.ajbedwars.AJBedwars
import org.bukkit.*
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import java.io.File

fun String.colour() = ChatColor.translateAlternateColorCodes('&', this)

fun String.debug() = if(AJBedwars.debug) Bukkit.broadcastMessage("&c(!) [DEBUG] (!)\n&b$this".colour()) else ""


fun ItemStack.setName(name: String): ItemStack {
    val meta = this.itemMeta ?: return this
    meta.setDisplayName(name)
    this.itemMeta = meta
    return this
}

fun String.toLocation(world: World): Location{
    val locs = this.split(",")
    return Location(world, locs[0].toDouble(), locs[1].toDouble(), locs[2].toDouble())
}

fun Int.roundTo9():Int =
    if(this % 9 == 0) this
    else ((this /9) + 1) *9

fun ItemStack.glow():ItemStack{
    val meta = itemMeta ?: return this
    meta.addEnchant(Enchantment.ARROW_INFINITE, 0, true)
    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
    this.itemMeta = meta
    return this
}