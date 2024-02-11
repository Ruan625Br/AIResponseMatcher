package com.jn.airesponsematcher.processor

import com.jn.airesponsematcher.operation.Operation

data class Output(
    val output: String, val operations: List<Operation>
)
