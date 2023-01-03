package de.cathesso.adventofcode2022

var checkForTotalContainment = false
class CleaningAssignment(rawAssignment:String){
    val sectionIdStart: Int = rawAssignment.substringBefore("-").toInt()
    val sectionIdEnd:Int = rawAssignment.substringAfter("-").toInt()
    val cleaningSections:MutableList<Int> = cleaningSectionLister(sectionIdStart,sectionIdEnd)

    private fun cleaningSectionLister(sectionIdStart: Int, sectionIdEnd: Int): MutableList<Int> {
        val cleaningSections: MutableList<Int> = mutableListOf()
        for(i in sectionIdStart..sectionIdEnd){
            cleaningSections.add(i)
        }
        return cleaningSections
    }

}

class CleaningPair(rawAssignments:String){
    val elfOne:CleaningAssignment = CleaningAssignment(rawAssignments.substringBefore(","))
    val elfTwo:CleaningAssignment = CleaningAssignment(rawAssignments.substringAfter(","))
    val overlapping:Boolean = overlapChecker(this.elfOne, this.elfTwo)
}

fun main(){
    val cleaningPairListRaw = loadInputFromTextAndSaveAsList("day_04_input.txt")
    var overlappingPairs: Int = 0
    val cleaningPairList:MutableList<CleaningPair> = mutableListOf<CleaningPair>()
    for (cleaningPair in cleaningPairListRaw){
        cleaningPairList.add(CleaningPair(cleaningPair))
    }
    for (cleaningPair in cleaningPairList){
        if (cleaningPair.overlapping){
            overlappingPairs += 1
        }
    }
    println("Overlapping Pairs: $overlappingPairs")
    checkForTotalContainment = false
}

private fun overlapChecker(elfOne: CleaningAssignment, elfTwo: CleaningAssignment): Boolean {
    if (checkForTotalContainment){
        return (elfOne.cleaningSections.containsAll(elfTwo.cleaningSections)||elfTwo.cleaningSections.containsAll(elfOne.cleaningSections))
    }
    else{
        for (section in elfOne.cleaningSections){
            if(elfTwo.cleaningSections.contains(section)){
                return true
            }
        }
    }
    return false
}

