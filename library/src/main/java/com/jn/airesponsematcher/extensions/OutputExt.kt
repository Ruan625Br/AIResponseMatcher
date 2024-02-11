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