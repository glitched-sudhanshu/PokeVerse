package org.example.pokeverse.core.domain

import kotlinx.coroutines.flow.Flow

interface AudioRepository {
    fun play(url: String)
    fun stop()
    fun release()
    fun pause()
    fun resume()
    fun stopAndResetAudioPlayer()
    fun setMute(isMuted: Boolean)
    val isPlaying: Flow<Boolean>
}