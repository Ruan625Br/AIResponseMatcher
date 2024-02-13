package com.jn.airesponsematcher.extensions

import com.jn.airesponsematcher.models.Arg
import com.jn.airesponsematcher.operation.Operation
import com.jn.airesponsematcher.operation.OperationBase
import com.jn.airesponsematcher.utils.Patterns

/**
 * Replaces the occurrence of a specific operation in the string with a new value.
 *
 * @param operation The operation to be replaced in the string.
 * @param newValue The new value that will replace the occurrence of the operation. Default: "".
 * @return A new string with the operation replaced by the new value.
 */
fun String.replaceOperationWithNewValue(operation: OperationBase, newValue: String = ""): String {
    return replace(operation.regex, newValue)
}

/**
 * Replaces all occurrences of specified operations in the string with a new value.
 *
 * @param operations The list of operations to be replaced in the string.
 * @param newValue The new value that will replace the occurrences of the operations. Default: "".
 * @return A new string with the operations replaced by the new value.
 */
fun String.replaceAllOperationsWithNewValue(operations: List<OperationBase>, newValue: String = ""): String {
    var result = this
    operations.forEach { result = result.replace(it.regex, newValue) }
    return result
}

/**
 * Extracts the arguments of an operation from the string.
 *
 * @param operation The operation from which to extract the arguments.
 * @return The arguments of the operation or null if there is no match.
 */
fun String.extractArgsFromOperation(operation: OperationBase): String? {
    val result = operation.regex.find(this)
    return result?.groupValues?.get(1)
}

/**
 * Converts a string of key-value pairs separated by commas into a map.
 *
 * @return A map containing the key-value pairs extracted from the string.
 */
fun String.toMapFromKeyValuePairs(): Map<String, String> {
    val keyValuePairs = split(", ")
    return keyValuePairs.associate { pair ->
        val (key, value) = pair.split(":")
        key.trim().removeSurrounding("\"") to value.trim().removeSurrounding("\"")
    }
}

/**
 * Converts a map of key-value pairs into a list of arguments.
 *
 * @return A list of arguments represented as instances of Arg.
 */
fun Map<String, String>.toArgList(): List<Arg> {
    return map { map ->
        Arg(map.key, map.value)
    }
}

/**
 * Adds an operation with specified arguments to the string.
 *
 * @param operation The operation to be added.
 * @param args The arguments to be included with the operation.
 * @return A new string with the added operation and arguments.
 */
fun String.addOperationWithArgs(operation: OperationBase, args: Map<String, String>): String {
    val mArgs = args.toArgList()
    val strArgs = mArgs.toString().removePrefix("[").removeSuffix("]")
    val value = this
    return buildString {
        append(value)
        appendLine("${operation.operationName}($strArgs)")
    }
}

/**
 * Removes double quotes from a string.
 *
 * @param newValue Optional value to replace double quotes. Default: an empty string.
 * @return A new string without double quotes.
 */
fun String.removeQuotes(newValue: String = ""): String {
    val regex = Patterns.QUOTES_BOUNDARIES.toRegex(RegexOption.MULTILINE)
    return replace(regex, newValue)
}

/**
 * Processes the matched text using the specified regex and resolves each match using the provided operation.
 *
 * @param matchedText The text matched by the regex.
 * @return A processed string containing the results of resolving each match.
 */
fun Operation.process(matchedText: String): String {
    val matches = regex.findAll(matchedText)
    val result = mutableListOf<String>()

    matches.forEach { matchResult ->
        val text = matchResult.value
        val args = matchResult.groupValues[1].toMapFromKeyValuePairs()
        result.add(resolve(text, args))
    }

    return result.joinToString("\n")
}
