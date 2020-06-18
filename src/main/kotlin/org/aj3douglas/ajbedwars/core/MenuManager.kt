package org.aj3douglas.ajbedwars.core

import me.mattstudios.mfgui.gui.guis.Gui
import me.mattstudios.mfgui.gui.guis.GuiItem
import org.aj3douglas.ajbedwars.utils.FileManager
import org.aj3douglas.ajbedwars.utils.colour
import org.aj3douglas.ajbedwars.utils.glow
import org.aj3douglas.ajbedwars.utils.roundTo9
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import kotlin.math.ceil
import kotlin.math.round

class MenuManager(private val fileManager: FileManager, private val javaPlugin: JavaPlugin) {

    fun setupMenu(menuName:String):Gui?{
        val menuMap = fileManager.readMenu(menuName) ?: return null
        val gui = Gui(javaPlugin, ceil(menuMap.size / 9.0).toInt(), menuName.colour())
        menuMap.forEach{(slot, data)->
            val itemStack = ItemStack(data.item.type)
            if(data.item.meta.glowing) itemStack.glow()
            val guiItem = GuiItem(itemStack) {event->
                event.isCancelled = true
                data.actions.forEach{
                    val array = it.split(" ")
                    if(it.startsWith("[item]")){
                        event.whoClicked.inventory.addItem(ItemStack(Material.valueOf(array[1]), array[2].toInt()))
                    }else if(it.startsWith("[command]")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), it.replace("[command]", "").replace("{player}", event.whoClicked.name))
                }
            }
            gui.setItem(slot - 1,guiItem)
        }
        return gui
    }
}