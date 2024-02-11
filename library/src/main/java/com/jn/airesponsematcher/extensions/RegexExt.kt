package com.jn.airesponsematcher.extensions

import com.jn.airesponsematcher.operation.Operation


internal fun Regex.searchContent(output: String): Map<String, String>? {
    val matchResult = find(output) ?: return null
    val content = matchResult.groupValues[1]

    return content.toMapFromKeyValuePairs()
}

internal fun Regex.resolve(operation: Operation, line: String): String {
    return operation.resolve(line, searchContent(line))
}

internal fun Regex.searchAllContent(output: String): Map<String, String> {
    val matchResult = findAll(output)
    val listContent = matchResult.map { it.groupValues[1] }.toList()
    val mapResult = mutableMapOf<String, String>()

    listContent.forEach {
        val map = it.toMapFromKeyValuePairs()
        mapResult.putAll(map)
    }
    return mapResult
}
