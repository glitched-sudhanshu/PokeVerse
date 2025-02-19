package org.example.pokeverse.core.presentation

import kotlinx.datetime.Clock

actual val currentTime: Long
    get() = Clock.System.now().toEpochMilliseconds()