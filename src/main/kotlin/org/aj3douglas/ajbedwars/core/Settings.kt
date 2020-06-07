package org.aj3douglas.ajbedwars.core

data class Settings(
    val gameSettings: GameSettings,
    val teams:MutableMap<String, Team>
)