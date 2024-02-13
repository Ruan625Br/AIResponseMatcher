package com.jn.airesponsematcher.operation

import com.jn.airesponsematcher.utils.Patterns

interface OperationBase {
    val operationName: String
    val pattern: String
        get() = Patterns.ARG_CONTENT

    val regex: Regex
        get() = Regex(operationName + pattern)
}