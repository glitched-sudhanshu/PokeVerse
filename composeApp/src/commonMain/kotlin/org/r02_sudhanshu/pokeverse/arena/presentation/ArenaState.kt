package org.r02_sudhanshu.pokeverse.arena.presentation

import androidx.compose.runtime.Immutable
import org.r02_sudhanshu.pokeverse.arena.data.Action

@Immutable
data class ArenaState(
    val ground: String,
    val firstPlayer: String,
    val secondPlayer: String,
    val firstPlayerActions: List<Action> = emptyList(),
    val secondPlayerActions: List<Action> = emptyList(),
    val firstPlayerHealth: Float = 100f,
    val secondPlayerHealth: Float = 100f,
    val firstPlayerHasSpecialAttack: Boolean = false,
    val secondPlayerHasSpecialAttack: Boolean = false,
    val gameOver: Boolean = firstPlayerHealth <= 0 || secondPlayerHealth <= 0
)