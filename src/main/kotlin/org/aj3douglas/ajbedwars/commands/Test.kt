package org.aj3douglas.ajbedwars.commands

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.base.CommandBase
import org.aj3douglas.ajbedwars.AJBedwars
import org.aj3douglas.ajbedwars.core.GameManager
import org.aj3douglas.ajbedwars.core.GeneratorManager
import org.aj3douglas.ajbedwars.core.StoreEntity
import org.aj3douglas.ajbedwars.core.StoreEntityManager
import org.aj3douglas.ajbedwars.utils.debug
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

@Command("test")
class Test(private val generatorManager:GeneratorManager, private val entityManager: StoreEntityManager):CommandBase() {
    @Default
    fun test(player:Player){
        generatorManager.createGenerators(player.world)
    }
    @SubCommand("kill")
    fun kill(player:Player){
        generatorManager.killGenerators()
    }
    @SubCommand("tier")
    fun setTier(player:Player, tier:String){
        generatorManager.setTier(player.world, tier.toInt())
    }
    @SubCommand("ekill")
    fun entityKill(player:Player){
        entityManager.kill()
    }
    @SubCommand("entity")
    fun entity(player:Player){
        entityManager.createEntities(player.world)
    }
}