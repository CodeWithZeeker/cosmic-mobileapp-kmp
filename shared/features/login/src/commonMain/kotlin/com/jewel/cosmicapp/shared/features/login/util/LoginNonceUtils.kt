package com.jewel.cosmicapp.shared.features.login.util

import kotlin.random.Random

fun generateNonce(length: Int = 32): String {
    val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (1..length)
        .map { Random.nextInt(0, charPool.size).let { charPool[it] } }
        .joinToString("")
}
