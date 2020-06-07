package org.aj3douglas.ajbedwars.core

import com.google.gson.annotations.SerializedName
import java.util.*
import javax.xml.stream.Location

data class Team(
        @SerializedName("spawn_point")
        val spawnPoint:Location,
        @SerializedName("iron_generator_location")
        val ironGeneratorLocation:Location,
        @SerializedName("item_store_villager")
        val itemStoreVillagerLocation:Location,
        @SerializedName("team_upgrades_villager")
        val teamUpgradesVillagerLocation:Location,
        @SerializedName("bed_location")
        val bedLocation: Location
)