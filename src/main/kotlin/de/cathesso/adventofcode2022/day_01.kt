package de.cathesso.adventofcode2022

import java.io.File

fun main(){
    //Load raw calories text
    val rawCaloriesList = File(ClassLoader.getSystemResource("day_01_input.txt").file).readLines()
    //Convert strings to longs and empty lines to null
    val caloriesList = rawCaloriesList.map { item -> item.toLongOrNull() }
    //Iterate through list and add up all calories until a null announces the next group
    val caloriesResolved = mutableListOf<Long>()
    var calorieCounter :Long = 0
    for (item in caloriesList){
        if (item != null){
            calorieCounter += item
        }else {
            caloriesResolved.add(calorieCounter)
            calorieCounter = 0
        }
    }
    caloriesResolved.add(calorieCounter)
    //Sort calories from biggest to smalles amount
    caloriesResolved.sortDescending()
    //print the biggest amount of calories
    println(caloriesResolved[0])
}

