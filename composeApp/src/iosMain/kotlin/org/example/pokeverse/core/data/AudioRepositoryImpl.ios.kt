package org.example.pokeverse.core.data

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.example.pokeverse.core.domain.AudioRepository
import platform.AVFAudio.AVAudioPlayer

actual class AudioRepositoryImpl(private val avAudioPlayer: AVAudioPlayer) : AudioRepository {
    override fun play(url: String) {
    }

    override fun stop() {
        avAudioPlayer.stop()
    }

    override fun release() {

    }

    override fun pause() {
        avAudioPlayer.pause()
    }

    override fun resume() {
        avAudioPlayer.play()
    }

    override fun stopAndResetAudioPlayer() {
        stop()
        avAudioPlayer.currentTime = 0.0
    }

    override fun setMute(isMuted: Boolean) {
        avAudioPlayer.volume = if (isMuted) 0f else 1f
    }

    override val isPlaying: Flow<Boolean>
        get() = callbackFlow {
            delay(1000)
            trySend(avAudioPlayer.isPlaying())
            awaitClose {}
        }
}