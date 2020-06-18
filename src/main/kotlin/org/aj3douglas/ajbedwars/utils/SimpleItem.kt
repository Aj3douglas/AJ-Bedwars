package org.aj3douglas.ajbedwars.utils

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

data class SimpleItem(private val type:Material, private val meta: SimpleMeta) {
    fun get():ItemStack = ItemStack(type).apply(meta::apply)
}

data class SimpleMeta(val name:String, val lore:List<String>){
    fun apply(item:ItemStack) {
        val meta = item.itemMeta ?: return
        meta.setDisplayName(name)
        meta.lore = lore
        item.itemMeta = meta
    }
}