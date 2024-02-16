package com.jn.airesponsematcher.utils

fun Map<String, String>.toRealPrimitiveType(): Map<String, Any?> {
    val mapCopy = mutableMapOf<String, Any?>()

    forEach { (key, value) ->
        mapCopy[key] = value.determinePrimitiveType()
    }
    return mapCopy
}

internal fun String.determinePrimitiveType(): Any {

    return toIntOrNull() ?: toDoubleOrNull() ?:
    toBigDecimalOrNull() ?: toFloatOrNull() ?: this

}