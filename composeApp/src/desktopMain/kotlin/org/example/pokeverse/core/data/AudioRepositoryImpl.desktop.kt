package org.example.pokeverse.core.data

import kotlinx.coroutines.flow.Flow
import org.example.pokeverse.core.domain.AudioRepository

actual class AudioRepositoryImpl : AudioRepository {
    override fun play(url: String, loop: Boolean) {
        TODO("Not yet implemented")
    }

    override fun stop() {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun stopAndResetAudioPlayer() {
        TODO("Not yet implemented")
    }

    override fun setMute(isMuted: Boolean) {
        TODO("Not yet implemented")
    }

    override val isPlaying: Flow<Boolean>
        get() = TODO("Not yet implemented")
}