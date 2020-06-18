package org.aj3douglas.ajbedwars.core.data

import org.bukkit.Material

class MenuData(val item: Item, val cost: String, val actions: List<String>) {

    class Item(val type: Material, val meta: Meta) {

        class Meta(val name: String, val glowing: Boolean)

    }

}