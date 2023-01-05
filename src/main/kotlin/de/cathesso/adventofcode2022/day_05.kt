package de.cathesso.adventofcode2022

const val crateMover9001 = true
fun main() {
    val rawInput = loadInputFromTextAndSaveAsList("day_05_input.txt")
    val indexOfBreakBetweenDrawingAndOperations = rawInput.indexOf("")
    val cratesDrawingRaw = rawInput.subList(0, indexOfBreakBetweenDrawingAndOperations - 1)
    val rawOperations = rawInput.subList(indexOfBreakBetweenDrawingAndOperations + 1, rawInput.lastIndex + 1)
    val operations: MutableList<CrateMovement> = mutableListOf()
    for (rawOperation in rawOperations) {
        operations.add(CrateMovement(rawOperation))
    }
    var cratesDrawing = createInitialMatrix(cratesDrawingRaw)
    println(operations[0].to)
    moveCrates(operations, cratesDrawing)
}

class CrateMovement(rawMovement: String) {
    private val splitRawMovement = rawMovement.split(" ")
    val howMany = splitRawMovement[1].toInt()
    val from = splitRawMovement[3].toInt() - 1
    val to = splitRawMovement[5].toInt() - 1
}

fun createInitialMatrix(cratesDrawingRaw: List<String>): MutableList<MutableList<String>> {
    val crates: MutableList<String> = mutableListOf("")
    val inverseStackIndex: MutableList<MutableList<String>> = mutableListOf()
    val stackIndex: MutableList<MutableList<String>> = mutableListOf()

    for (crate in cratesDrawingRaw[0].windowed(4, 4, true)) {
        inverseStackIndex.add(mutableListOf(""))
    }
    for ((crateHeight, line) in cratesDrawingRaw.withIndex()) {
        for ((stackCounter, crate) in line.windowed(3, 4, true).withIndex()) {
            if (crate.replace(" ", "") != "") {
                inverseStackIndex[stackCounter].add(crate)
            }
        }
    }
    for (stack in inverseStackIndex) {
        stack.removeAt(0)
        //stack.add(stack.lastIndex + 1,"end")
        stackIndex.add(stack.asReversed())
    }
    println(stackIndex)
    return stackIndex
}

fun moveCrates(movements: MutableList<CrateMovement>, cratesDrawing: MutableList<MutableList<String>>) {
    var finalCratesDrawing = cratesDrawing
    for (crateStacks in finalCratesDrawing) {
        println(crateStacks)
    }
    for (movement in movements) {
        if (crateMover9001) {
            for (i in 0 until movement.howMany) {
                val thisCrate = finalCratesDrawing[movement.from][finalCratesDrawing[movement.from].lastIndex - movement.howMany +1 + i]
                finalCratesDrawing[movement.to].add(thisCrate)
                finalCratesDrawing[movement.from].removeAt(finalCratesDrawing[movement.from].lastIndex - movement.howMany +1 + i)
            }
        } else {
            for (i in 0 until movement.howMany) {
                val thisCrate = finalCratesDrawing[movement.from].last()
                finalCratesDrawing[movement.to].add(thisCrate)
                finalCratesDrawing[movement.from].removeLast()
            }
        }
        println("*********************************")
        for (crateStacks in finalCratesDrawing) {
            println(crateStacks)
        }
    }
    //println(finalCratesDrawing)
}