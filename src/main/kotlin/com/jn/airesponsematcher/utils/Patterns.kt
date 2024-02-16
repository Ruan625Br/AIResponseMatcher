package com.jn.airesponsematcher.utils

object Patterns {
    const val ARG_CONTENT  = "\\((.+?)\\)"
    const val QUOTES_BOUNDARIES = "^\"|\"$"
    const val BASE_ARGUMENT = "(.+?)"
    const val ARG_CUSTOM = "START${BASE_ARGUMENT}END"
}