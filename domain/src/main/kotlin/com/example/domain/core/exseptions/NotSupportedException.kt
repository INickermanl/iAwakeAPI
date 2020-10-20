package com.example.domain.core.exseptions

sealed class NotSupportedException(val reason: String) : RuntimeException()