package org.example.pokeverse

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform