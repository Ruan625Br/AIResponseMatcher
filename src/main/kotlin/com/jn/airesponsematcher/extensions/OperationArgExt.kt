package com.jn.airesponsematcher.extensions

import com.jn.airesponsematcher.operation.OperationArg

operator fun Map<String, String>.get(arg: OperationArg): String? {
    return get(arg.value)
}