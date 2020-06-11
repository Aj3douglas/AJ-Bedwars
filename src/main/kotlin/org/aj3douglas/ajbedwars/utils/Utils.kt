package org.aj3douglas.ajbedwars.utils

object Utils {
    var holograms:Boolean = false
    fun printStartupMessage(version:String, authors:List<String>, desc:String?){
        """
            &a----------<&bAJ Bedwars&a>----------
            &aVersion:&b $version
            &aAuthors: [&b${authors.joinToString(", ")}]
            &aDescription: &b$desc
            &a----------<&bAJ Bedwars&a>----------
        """.trimIndent().colour()
    }
}