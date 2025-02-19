package org.example.pokeverse.core.presentation

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role

fun Modifier.clickableSingle(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    ripple: Boolean = true,
    onClick: () -> Unit
) = this.composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickableSingle"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    val multipleEventsCutter = remember { MultipleEventsCutter.create() }
    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { multipleEventsCutter.processEvent { onClick() } },
        role = role,
        indication = if (ripple) LocalIndication.current else null,
        interactionSource = remember { MutableInteractionSource() }
    )
}

fun Modifier.clickableWithoutRipple(
    onClick: () -> Unit,
) = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick,
    )
}


interface MultipleEventsCutter {
    fun processEvent(event: () -> Unit)

    companion object
}

fun MultipleEventsCutter.Companion.create(): MultipleEventsCutter =
    MultipleEventsCutterImpl()

private class MultipleEventsCutterImpl : MultipleEventsCutter {
    private val now: Long
        get() = currentTime

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= 300L) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}

expect val currentTime: Long