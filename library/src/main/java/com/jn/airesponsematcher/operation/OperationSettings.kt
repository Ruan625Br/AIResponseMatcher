package com.jn.airesponsematcher.operation

import com.jn.airesponsematcher.utils.Patterns

data class OperationSettings(
    val argRegex: Regex = Regex(Patterns.ARG)
)
