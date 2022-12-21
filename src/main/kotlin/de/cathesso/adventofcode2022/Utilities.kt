package de.cathesso.adventofcode2022

import java.io.File

fun loadInputFromTextAndSaveAsList(textFile: String): List<String> {
    return File(ClassLoader.getSystemResource(textFile).file).readLines()
}