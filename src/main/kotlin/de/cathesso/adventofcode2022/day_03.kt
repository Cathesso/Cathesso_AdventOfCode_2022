package de.cathesso.adventofcode2022

val alphabetNumeric = ('a' .. 'z').toMutableList() + ('A'..'Z').toMutableList()

class Backpack (items: String){
    val allItems = items
    val compartmentA: String = items.substring(0, items.length /2)
    val compartmentB: String = items.substring(items.length/2)
    val itemsInBothCompartments: MutableList<Char> = backpackCompartmentComparer(this)
}

fun main(){
    val backpackListRaw = loadInputFromTextAndSaveAsList("day_03_input.txt")
    val backpackList = backpackMapper(backpackListRaw)
    for (backpack in backpackList){
        println("A: " + backpack.compartmentA)
        println("B: " + backpack.compartmentB)
        println("Item(s)" + backpack.itemsInBothCompartments)
    }
    println("Total Priority Count: " + backpackItemInBothCompartmentsPriorityCountUp(backpackList))
}

fun backpackMapper(backpackListRaw: List<String>): MutableList<Backpack>{
    val backpackList :MutableList<Backpack> = mutableListOf()
    for (backpackRaw in backpackListRaw){
        backpackList.add(Backpack(backpackRaw))
    }
    return backpackList
}

fun backpackCompartmentComparer(backpack: Backpack): MutableList<Char>{
    val itemsInBothCompartments :MutableList<Char> = mutableListOf()
    for (item in backpack.compartmentA){
        if (backpack.compartmentB.contains(item) && !itemsInBothCompartments.contains(item)){
            itemsInBothCompartments.add(item)
        }
    }
    return itemsInBothCompartments
}


fun backpackItemInBothCompartmentsPriorityCountUp(backpackList :MutableList<Backpack>) :Int{
    var priorityCountUp :Int = 0
    for (backpack in backpackList){
        for (item in backpack.itemsInBothCompartments){
            priorityCountUp += (alphabetNumeric.indexOf(item) + 1)
        }
    }
    return priorityCountUp
}


//Jeder Rucksack hat zwei Behälter
//Alle Items von einem Typ sollen in jeweils einen Behälter
