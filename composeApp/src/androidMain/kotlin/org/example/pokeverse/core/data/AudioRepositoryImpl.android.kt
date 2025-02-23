package org.example.pokeverse.core.data

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.REPEAT_MODE_OFF
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.example.pokeverse.core.domain.AudioRepository

actual class AudioRepositoryImpl(private val exoPlayer: ExoPlayer) :
    AudioRepository {
    override fun play(url: String) {
        exoPlayer.setMediaItem(MediaItem.fromUri(url))
        exoPlayer.repeatMode = REPEAT_MODE_OFF
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        exoPlayer.play()
    }

    override fun stop() {
        exoPlayer.stop()
    }

    override fun release() {
        exoPlayer.release()
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun resume() {
        exoPlayer.play()
    }

    override fun stopAndResetAudioPlayer() {
        exoPlayer.stop()
        exoPlayer.release()
    }

    override fun setMute(isMuted: Boolean) {
        exoPlayer.volume = if (isMuted) 0f else 1f
    }

    override val isPlaying: Flow<Boolean>
        get() = callbackFlow {
            val listener = object : Player.Listener {
                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    trySend(isPlaying)
                }
            }
            exoPlayer.addListener(listener)
            awaitClose { exoPlayer.removeListener(listener) }
        }

}