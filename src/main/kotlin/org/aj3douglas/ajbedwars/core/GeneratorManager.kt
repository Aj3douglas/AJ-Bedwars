package org.aj3douglas.ajbedwars.core

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI
import com.google.common.reflect.TypeToken
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import org.aj3douglas.ajbedwars.AJBedwars
import org.aj3douglas.ajbedwars.utils.*
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import java.io.FileReader


class GeneratorManager(private val javaPlugin: JavaPlugin, private val fileManager: FileManager) {
    private val tasks = mutableListOf<BukkitTask>()
    private val holograms = Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")
    fun createGenerators(world:World){
        fileManager.readGenerators().forEach{generator->
            generator.locations.forEach locationForEach@{location->
                if(!holograms) return@locationForEach
                val holo = HologramsAPI.createHologram(javaPlugin, location.toLocation(world).add(0.0,4.0, 2.0))
                holo.appendTextLine("&aGenerator: &b${generator.name}".colour())
                holo.appendTextLine("&aTier: &b1".colour())
            }
            val speedOne = generator.tierSpeed[1]?.toLong()
            if(speedOne == null){
                "Config Error".debug()
                return
            }
            tasks.add(object : BukkitRunnable() {
                override fun run() {
                    generator.locations.forEach{
                        world.dropItemNaturally(it.toLocation(world).add(0.0, 2.0,0.0), ItemStack(generator.item).setName(generator.displayName))
                    }
                }
            }.runTaskTimer(javaPlugin, 0L, speedOne *20))
        }
    }

    fun setTier(world:World, tier:Int){
         fileManager.readGenerators().forEach{generator->
            generator.locations.forEach locationForEach@{location->
                if(!holograms) return@locationForEach
                val holo = HologramsAPI.createHologram(javaPlugin, location.toLocation(world).add(0.0,4.0, 2.0))
                holo.appendTextLine("&aGenerator: &b${generator.name}".colour())
                holo.appendTextLine("&aTier: &b$tier".colour())
            }
            val tierSpeed = generator.tierSpeed[tier]?.toLong()
            if(tierSpeed == null){
                "Config Error".debug()
                return
            }
            tasks.add(object : BukkitRunnable() {
                override fun run() {
                    generator.locations.forEach{
                        world.dropItemNaturally(it.toLocation(world).add(0.0, 2.0,0.0), ItemStack(generator.item).setName(generator.displayName))
                    }
                }
            }.runTaskTimer(javaPlugin, 0L, tierSpeed *20))
        }
    }

    fun killGenerators(){
        tasks.forEach{ it.cancel() }
        HologramsAPI.getHolograms(javaPlugin).forEach{ it.delete() }
    }

}