package org.r02_sudhanshu.pokeverse.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.r02_sudhanshu.pokeverse.core.domain.AudioRepository

class AudioViewModel(private val audioRepository: AudioRepository) : ViewModel() {

    private val _isPlaying = audioRepository.isPlaying.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    private val isMuted = MutableStateFlow(false)

    private fun toggleMute() {
        isMuted.value = !isMuted.value
        viewModelScope.launch {
            audioRepository.setMute(isMuted.value)
        }
    }

    fun play(url: String, loop: Boolean = false) {
        audioRepository.stop()
        audioRepository.play(url, loop)
        audioRepository.setMute(false)
        isMuted.value = false
    }

    fun stop() = audioRepository.stop()

    private fun onPause() = audioRepository.pause()

    private fun onResume() = audioRepository.resume()

    private fun stopAndClearAudio() {
        try {
            audioRepository.stopAndResetAudioPlayer()
        } catch (_: Exception) {

        }
    }
}