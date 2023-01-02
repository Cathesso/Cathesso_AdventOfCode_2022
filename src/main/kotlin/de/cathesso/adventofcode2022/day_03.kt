package de.cathesso.adventofcode2022

val alphabetNumeric = ('a' .. 'z').toMutableList() + ('A'..'Z').toMutableList()

class Backpack (items: String){
    val allItems = items
    val compartmentA: String = items.substring(0, items.length /2)
    val compartmentB: String = items.substring(items.length/2)
    val duplicates: MutableList<Char> = itemListComparer(compartmentA, compartmentB)
}

fun main(){
    val backpackListRaw = loadInputFromTextAndSaveAsList("day_03_input.txt")
    val backpackList = backpackMapper(backpackListRaw)
    println("Task one: Single backpacks")
    for (backpack in backpackList){
        println("A: " + backpack.compartmentA)
        println("B: " + backpack.compartmentB)
        println("Item(s)" + backpack.duplicates)
    }
    println("Total Priority Count: " + backpackDuplicatesPriorityCountUp(backpackList))
    println('*'.toString().repeat(42))
    println("Task two: Badges of three backpacks: ")
    val badges = compareTripleBackpacks(backpackList)
    println(badges)
    println("Total Priority Count: " + priorityCountUp(badges))
}

fun backpackMapper(backpackListRaw: List<String>): MutableList<Backpack>{
    val backpackList :MutableList<Backpack> = mutableListOf()
    for (backpackRaw in backpackListRaw){
        backpackList.add(Backpack(backpackRaw))
    }
    return backpackList
}

fun itemListComparer(itemListOne :String, itemListTwo :String): MutableList<Char>{
    val itemsInBothLists :MutableList<Char> = mutableListOf()
    for (item in itemListOne){
        if (itemListTwo.contains(item) && !itemsInBothLists.contains(item)){
            itemsInBothLists.add(item)
        }
    }
    return itemsInBothLists
}

fun backpackDuplicatesPriorityCountUp(backpackList :MutableList<Backpack>) :Int{
    var priorityCountUp :Int = 0
    for (backpack in backpackList){
        priorityCountUp += priorityCountUp(backpack.duplicates)
    }
    return priorityCountUp
}

fun priorityCountUp (itemList :MutableList<Char>): Int{
    var priorityCountUp :Int = 0
    for (item in itemList){
        priorityCountUp += (alphabetNumeric.indexOf(item) + 1)
    }
    return priorityCountUp
}

fun compareTripleBackpacks(backpackList: MutableList<Backpack>) :MutableList<Char>{
    val badges :MutableList<Char> = mutableListOf()
    for (i in 0..backpackList.lastIndex step 3){
        val comparisonOne = itemListComparer(backpackList[i].allItems, backpackList[i+1].allItems).toString()
        val comparisonTwo = itemListComparer(comparisonOne, backpackList[i+2].allItems)
        for (item in comparisonTwo){
            badges.add(item)
        }
    }
    return badges
}
