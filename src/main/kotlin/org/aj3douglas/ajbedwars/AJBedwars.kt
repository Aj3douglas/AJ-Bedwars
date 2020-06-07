package org.aj3douglas.ajbedwars

import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import org.aj3douglas.ajbedwars.core.Generators
import org.aj3douglas.ajbedwars.core.Teams
import org.aj3douglas.ajbedwars.utils.colour
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.FileReader
import java.io.Reader
import java.nio.file.Path

class AJBedwars:JavaPlugin() {
    private val gson  = GsonBuilder().create()
    var teamsFile = File("${this.dataFolder}/data/teams.json")
    var generatorsFile = File("${this.dataFolder}/data/generators.json")
    override fun onEnable() {
        println("""
            &a----------<&bAJ Bedwars&a>----------
            &aVersion:&b ${this.description.version}
            &aAuthors: [&b${this.description.authors.joinToString(", ")}]
            &aDescription: &b${this.description.description}
            &a----------<&bAJ Bedwars&a>----------
        """.trimIndent().colour())
    }

    fun getTeams():Teams = gson.fromJson(teamsFile.readText(), Teams::class.java)
    fun getGenerators():Generators = gson.fromJson(generatorsFile.readText(), Generators::class.java)


}