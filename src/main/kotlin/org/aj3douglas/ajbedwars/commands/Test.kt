package org.aj3douglas.ajbedwars.commands

import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.annotations.SubCommand
import me.mattstudios.mf.base.CommandBase
import org.aj3douglas.ajbedwars.core.GeneratorManager
import org.aj3douglas.ajbedwars.core.MenuManager
import org.aj3douglas.ajbedwars.core.StoreEntityManager
import org.bukkit.entity.Player

@Command("test")
class Test(private val generatorManager: GeneratorManager, private val storeEntityManager: StoreEntityManager, private val menuManager: MenuManager) :
    CommandBase() {
    @Default
    fun test(player: Player) {
        generatorManager.createGenerators(player.world)
    }

    @SubCommand("kill")
    fun kill(player: Player) {
        generatorManager.killGenerators()
        storeEntityManager.killEntities()
    }

    @SubCommand("tier")
    fun setTier(player: Player, tier: String) {
        generatorManager.setTier(player.world, tier.toInt())
    }

    @SubCommand("entity")
    fun entity(player:Player){
        storeEntityManager.createEntities(player.world)
    }
    
    @SubCommand("gui")
    fun gui(player:Player, file:String){
        val gui = menuManager.setupMenu(file) ?: return
        gui.open(player)
    }

}