package com.jn.airesponsematcher.extensions

import com.jn.airesponsematcher.processor.Output


fun Output.processLines(): String {
    val lines = output.lines()
    val linesCopy = lines.toMutableList()

    lines.forEachIndexed { index, line ->
        operations.forEach { operation ->
            val regex = operation.regex
            if (regex.containsMatchIn(line)){
                linesCopy[index] = regex.resolve(operation, line)
            }
        }
    }

    return linesCopy.joinToString("\n")
}

fun Output.process(): String {
    val outputMatches = mutableListOf<String>()
    var currentPosition = 0

    operations.forEach { operation ->
        val regex = operation.regex
        val matches = regex.findAll(output)
        matches.forEach { matchResult ->

            outputMatches.add(output.substring(currentPosition, matchResult.range.first))
            outputMatches.add(operation.process(matchResult.value))
            currentPosition = matchResult.range.last + 1
        }
    }
    outputMatches.add(output.substring(currentPosition))
    return outputMatches.joinToString("\n")
}