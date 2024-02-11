package com.jn.airesponsematcher.models

data class Arg(val key: String, val value: String){

    override fun toString(): String {
        return "\"$key\": \"$value\""
    }
}
