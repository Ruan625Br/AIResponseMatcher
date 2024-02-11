package com.jn.airesponsematcher.operation

 interface Operation : OperationBase {

    fun resolve(output: String, args: Map<String, String>?): String

    val name: String

    override val operationName: String
        get() = name
}