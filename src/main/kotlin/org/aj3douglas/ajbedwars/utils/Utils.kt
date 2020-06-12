package org.aj3douglas.ajbedwars.utils

import com.google.common.reflect.TypeToken
import org.aj3douglas.ajbedwars.core.Generator
import org.aj3douglas.ajbedwars.core.StoreEntity

fun printStartupMessage(version: String, authors: List<String>, desc: String?) {
    """
            &a----------<&bAJ Bedwars&a>----------
            &aVersion:&b $version
            &aAuthors: [&b${authors.joinToString(", ")}]
            &aDescription: &b$desc
            &a----------<&bAJ Bedwars&a>----------
        """.trimIndent().colour()
}

val generatorTypetoken = object : TypeToken<List<Generator>>() {}.type
val entityTypeToken = object : TypeToken<List<StoreEntity>>() {}.type
