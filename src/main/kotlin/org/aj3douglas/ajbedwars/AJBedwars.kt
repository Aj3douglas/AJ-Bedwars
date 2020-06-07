package org.aj3douglas.ajbedwars

import com.google.common.reflect.TypeToken
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import org.aj3douglas.ajbedwars.core.Settings
import org.aj3douglas.ajbedwars.core.Team
import org.aj3douglas.ajbedwars.utils.AJException
import org.aj3douglas.ajbedwars.utils.colour
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.FileReader
import java.io.Reader
import java.nio.file.Path

class AJBedwars:JavaPlugin() {
    private val gson  = GsonBuilder().create()
    var settingsFile = File("${this.dataFolder}/data/teams.json")
    override fun onEnable() {
        println("""
            &a----------<&bAJ Bedwars&a>----------
            &aVersion:&b ${this.description.version}
            &aAuthors: [&b${this.description.authors.joinToString(", ")}]
            &aDescription: &b${this.description.description}
            &a----------<&bAJ Bedwars&a>----------
        """.trimIndent().colour())
    }

    fun getSettings():Settings = gson.fromJson(settingsFile.readText(), Settings::class.java)


}