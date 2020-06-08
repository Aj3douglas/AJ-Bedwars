package org.aj3douglas.ajbedwars.core

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI
import com.google.common.reflect.TypeToken
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import org.aj3douglas.ajbedwars.AJBedwars
import org.aj3douglas.ajbedwars.utils.colour
import org.aj3douglas.ajbedwars.utils.debug
import org.aj3douglas.ajbedwars.utils.setName
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask
import java.io.FileReader


class GeneratorManager(private val plugin:AJBedwars) {
    val gson = GsonBuilder().create()
    private val tasks = mutableListOf<BukkitTask>()

    fun createGenerators(world:World){
        readGenerators().forEach{generator->
            generator.locations.forEach locationForEach@{location->
                if(!plugin.holoGrams) return@locationForEach
                val holo = HologramsAPI.createHologram(plugin, location.toLocation(world).add(0.0,4.0, 2.0))
                holo.appendTextLine("&aGenerator: &b${generator.name}".colour())
                holo.appendTextLine("&aTier: &b1".colour())
            }
            val speedOne = generator.tierSpeeds[1]?.toLong()
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
            }.runTaskTimer(plugin, 0L, speedOne *20))
        }
    }

    fun setTier(world:World, tier:Int){
        killGenerators()
        readGenerators().forEach{generator->
            generator.locations.forEach locationForEach@{location->
                if(!plugin.holoGrams) return@locationForEach
                val holo = HologramsAPI.createHologram(plugin, location.toLocation(world).add(0.0,4.0, 2.0))
                holo.appendTextLine("&aGenerator: &b${generator.name}".colour())
                holo.appendTextLine("&aTier: &b$tier".colour())
            }
            val tierSpeed = generator.tierSpeeds[tier]?.toLong()
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
            }.runTaskTimer(plugin, 0L, tierSpeed *20))
        }
    }

    fun killGenerators(){
        tasks.forEach{ it.cancel() }
        HologramsAPI.getHolograms(plugin).forEach{ it.delete() }
    }


    private fun readGenerators():List<Generator> = gson.fromJson(
        JsonReader(FileReader(plugin.generatorsFile)),
        object : TypeToken<List<Generator>>() {}.type
    )
}