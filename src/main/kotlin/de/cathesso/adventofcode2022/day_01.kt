package de.cathesso.adventofcode2022

import java.io.File

fun main(){
    val rawInput = loadInputFromText("day_01_input.txt")
    val sortedCalories = groupCalories(rawInput)
    println("Elf with most calories: " + sortedCalories[0])
    println("Top three elves calories sum: " + sumUpTopCalories(3, sortedCalories))
}


fun loadInputFromText(textFile :String) :List<Long?> {
    //Load raw calories text
    val rawCaloriesList = File(ClassLoader.getSystemResource(textFile).file).readLines()
    //Convert strings to longs and empty lines to null
    return rawCaloriesList.map { item -> item.toLongOrNull() }
}
fun groupCalories(listOfAddedUpCalories :List<Long?>) :MutableList<Long>{
    //Iterate through list and add up all calories until a null announces the next group
    val caloriesResolved = mutableListOf<Long>()
    var calorieCounter: Long = 0
    for (item in listOfAddedUpCalories) {
        if (item != null) {
            calorieCounter += item
        } else {
            caloriesResolved.add(calorieCounter)
            calorieCounter = 0
        }
    }
    caloriesResolved.add(calorieCounter)
    //Sort calories from biggest to smallest amount
    caloriesResolved.sortDescending()
    return caloriesResolved
}

fun sumUpTopCalories(amountOfTopElves :Int, groupedCalories :List<Long>) :Long{
    var addedCalories :Long = 0
    for (i in 1..amountOfTopElves){
        addedCalories += groupedCalories[i-1]
    }
    return addedCalories
}
