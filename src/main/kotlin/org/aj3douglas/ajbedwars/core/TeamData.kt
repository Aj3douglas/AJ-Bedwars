package org.aj3douglas.ajbedwars.core

import com.google.gson.annotations.SerializedName
import org.bukkit.Location
import java.util.*

data class TeamData(
        val spawnPoint:Location,
        val ironGeneratorLocation:Location,
        @SerializedName("item_store_villager")
        val itemStoreVillagerLocation:Location,
        @SerializedName("team_upgrades_villager")
        val teamUpgradesVillagerLocation: Location,
        val bedLocation: Location
)