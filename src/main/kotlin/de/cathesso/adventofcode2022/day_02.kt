package de.cathesso.adventofcode2022

enum class HandShapes { ROCK, PAPER, SCISSORS }
fun main() {
    val rawGames = loadInputFromTextAndSaveAsList("day_02_input.txt")

    var totalScore: Long = 0
    println(rawGames)
    println("Wrong calculated Games:")
    var gameNumber = 1
    for (game in rawGames) {
        val thisMatch = matchCreator(game)
        val matchPoints = totalPoints(thisMatch)
        totalScore += matchPoints
        println("Game $gameNumber: $thisMatch --> $matchPoints Points")
        gameNumber++
    }
    println("Total Score: $totalScore")
    println("-----------------------------------------")
    println("Corrected Games:")
    gameNumber = 1
    totalScore = 0
    for (game in rawGames) {
        val thisMatch = correctMatchCreator(game)
        val matchPoints = totalPoints(thisMatch)
        totalScore += matchPoints
        println("Game $gameNumber: $thisMatch --> $matchPoints Points")
        gameNumber++
    }
    println("Total Score: $totalScore")
}

data class Match(
    var myHand: String,
    var opponentHand: String,
    var matchResult: String
)

fun matchCreator(rawMatch: String): Match {
    var myHand: String = ""
    var opponentHand: String = ""
    var matchResult: String = ""
    when (rawMatch.first().toString()) {
        "A" -> opponentHand = "Rock"
        "B" -> opponentHand = "Paper"
        "C" -> opponentHand = "Scissors"
    }
    when (rawMatch.last().toString()) {
        "X" -> myHand = "Rock"
        "Y" -> myHand = "Paper"
        "Z" -> myHand = "Scissors"
    }
    matchResult = matchEvaluator(myHand, opponentHand)

    return Match(myHand, opponentHand, matchResult)
}

fun correctMatchCreator(rawMatch: String): Match {
    var myHand: String = ""
    var opponentHand: String = ""
    var matchResult: String = ""
    when (rawMatch.first().toString()) {
        "A" -> opponentHand = "Rock"
        "B" -> opponentHand = "Paper"
        "C" -> opponentHand = "Scissors"
    }
    when (rawMatch.last().toString()) {
        "X" -> matchResult = "LOOSE"
        "Y" -> matchResult = "DRAW"
        "Z" -> matchResult = "WIN"
    }

    myHand = correctMatchEvaluator(matchResult, opponentHand)
    return Match(myHand, opponentHand, matchResult)
}

fun matchEvaluator(myHand: String, opponentHand: String): String {
    when (myHand) {
        opponentHand -> {
            return "DRAW"
        }

        "Rock" -> {
            when (opponentHand) {
                "Paper" -> return "LOOSE"
                "Scissors" -> return "WIN"
            }
        }

        "Paper" -> {
            when (opponentHand) {
                "Scissors" -> return "LOOSE"
                "Rock" -> return "WIN"
            }
        }

        "Scissors" -> {
            when (opponentHand) {
                "Rock" -> return "LOOSE"
                "Paper" -> return "WIN"
            }
        }
    }
    return "Invalid Match"
}

fun correctMatchEvaluator(matchResult: String, opponentHand: String): String {
    when (matchResult) {
        "DRAW" -> return opponentHand
        "LOOSE" -> {
            when (opponentHand) {
                "Rock" -> return "Scissors"
                "Paper" -> return "Rock"
                "Scissors" -> return "Paper"
            }
        }

        "WIN" -> {
            when (opponentHand) {
                "Rock" -> return "Paper"
                "Paper" -> return "Scissors"
                "Scissors" -> return "Rock"
            }
        }
    }
    return "Invalid Match"
}

fun totalPoints(match: Match): Long {
    var handPoints: Long = 0
    var matchPoints: Long = 0
    when (match.myHand) {
        "Rock" -> handPoints = 1
        "Paper" -> handPoints = 2
        "Scissors" -> handPoints = 3
    }
    when (match.matchResult) {
        "WIN" -> matchPoints = 6
        "DRAW" -> matchPoints = 3
        "LOOSE" -> matchPoints = 0
    }
    return handPoints + matchPoints
}

