package com.jn.airesponsematcher.utils

import kotlin.reflect.KParameter
import kotlin.reflect.full.primaryConstructor

inline fun <reified T : Any> buildClass(vararg args: Any?): T {
    val constructor = T::class.primaryConstructor
        ?: throw IllegalArgumentException("The class ${T::class.simpleName} has no primary constructor")

    val params = constructor.parameters
    if (args.size == params.size) {
        val argMap = params.zip(args).toMap()
        return constructor.callBy(argMap)
    } else {
        throw IllegalArgumentException("Incorrect number of arguments\nExpected: ${params.size}\nFound: ${args.size}")
    }
}

inline fun <reified T : Any> buildClass(args: Map<String, Any?>): T {

    val constructor = T::class.primaryConstructor
        ?: throw IllegalArgumentException("The class ${T::class.simpleName} has no primary constructor")

    val params = constructor.parameters

    if (args.size > params.size){
        throw IllegalArgumentException("Excessive number of arguments\nExpected: ${params.size}\nFound: ${args.size}")
    }

    val argsMap = mutableMapOf<KParameter, Any?>()

    for ((name, value ) in args){
        val parameter = params.find { it.name == name } ?: throw IllegalArgumentException("The $name parameter does not exist in the constructor of ${T::class.simpleName}")
        argsMap[parameter] = value
    }

    return constructor.callBy(argsMap)
}