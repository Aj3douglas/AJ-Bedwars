package org.aj3douglas.ajbedwars.core

import java.util.*
import javax.xml.stream.Location

data class Team(
        val spawnPoint:Location,
        val ironGeneratorLocation:Location,
        val diamondGeneratorLocation:Location,
        val itemStoreVillagerLocation:Location,
        val teamUpgradesVillagerLocation:Location,
        val bedLocation: Location
)