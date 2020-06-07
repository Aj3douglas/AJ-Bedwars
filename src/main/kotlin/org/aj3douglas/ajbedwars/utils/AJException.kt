package org.aj3douglas.ajbedwars.utils

import org.bukkit.Bukkit
import java.lang.Exception

class AJException(exc:String):Exception(exc){
    init{
        Bukkit.broadcastMessage("&a[AJ Bedwars] &b$exc")
    }
}