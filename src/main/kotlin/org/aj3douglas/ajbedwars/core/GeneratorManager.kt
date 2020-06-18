package org.aj3douglas.ajbedwars.core

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI
import org.aj3douglas.ajbedwars.utils.*
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask


class GeneratorManager(private val javaPlugin: JavaPlugin, private val fileManager: FileManager) {
    private val tasks = mutableListOf<BukkitTask>()
    fun createGenerators(world: World) {
        val holograms = Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")
        val generators = fileManager.readGenerators() ?: return
        generators.forEach{ (name, data) ->
            data.locations.forEach locationForEach@{ location ->
                if (!holograms) return@locationForEach
                val holo = HologramsAPI.createHologram(javaPlugin, location.toLocation(world).add(0.0, 3.0, 2.0))
                holo.appendTextLine("&aGenerator: &b$name".colour())
                holo.appendTextLine("&aTier: &b1".colour())
            }
            val speedOne = data.speedTiers[0]?.toLong()
            if (speedOne == null) {
                "Config Error".debug()
                return@forEach
            }
            tasks.add(object : BukkitRunnable() {
                override fun run() {
                    data.locations.forEach {
                        val mat = Material.matchMaterial(data.item.type) ?: return
                        world.dropItemNaturally(
                            it.toLocation(world).add(0.0, 2.0, 0.0),
                            ItemStack(mat).setName(data.item.meta.name)
                        )
                    }
                }
            }.runTaskTimer(javaPlugin, 0L, ((20* 60) / speedOne)))
        }

    }

    fun setTier(world: World, tier: Int) {
        killGenerators()
        val holograms = Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")
        val generators = fileManager.readGenerators() ?: return
        generators.forEach{ (name, data) ->
            data.locations.forEach locationForEach@{ location ->
                if (!holograms) return@locationForEach
                val holo = HologramsAPI.createHologram(javaPlugin, location.toLocation(world).add(0.0, 3.0, 2.0))
                holo.appendTextLine("&aGenerator: &b$name".colour())
                holo.appendTextLine("&aTier: &b$tier".colour())
            }
            val speedOne = data.speedTiers[0]?.toLong()
            if (speedOne == null) {
                "Config Error".debug()
                return@forEach
            }
            tasks.add(object : BukkitRunnable() {
                override fun run() {
                    data.locations.forEach {
                        val mat = Material.matchMaterial(data.item.type) ?: return
                        world.dropItemNaturally(
                            it.toLocation(world).add(0.0, 2.0, 0.0),
                            ItemStack(mat).setName(data.item.meta.name)
                        )
                    }
                }
            }.runTaskTimer(javaPlugin, 0L, ((20* 60) / tier).toLong()))
        }
    }
    fun killGenerators() {
        tasks.forEach { it.cancel() }
        HologramsAPI.getHolograms(javaPlugin).forEach { it.delete() }
    }
}