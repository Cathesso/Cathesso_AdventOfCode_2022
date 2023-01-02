package de.cathesso.adventofcode2022

fun main(){
    val buchstaben = listOf("x", "a", "y", "b", "z", "c")
    for (wort in buchstaben){
        println(figurenAuswerter(wort))
    }


}

fun figurenAuswerter(wort:String) :String{
    return when (wort) {
        "a", "x" ->  "Rock"
        "b", "y" ->  "Paper"
        "c", "z" ->  "Scissors"
        else -> "Error"
    }
}