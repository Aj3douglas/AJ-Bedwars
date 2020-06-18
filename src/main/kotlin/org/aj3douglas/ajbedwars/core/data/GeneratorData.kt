package org.aj3douglas.ajbedwars.core.data

class GeneratorData(val item: Item, val speedTiers:List<Int>, val timeToTiers:List<Int>, val locations:List<String>) {

    class Item(val type: String, val meta: Meta) {

        class Meta(val name: String, val glowing: Boolean)


    }

}