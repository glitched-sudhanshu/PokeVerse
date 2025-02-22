package org.example.pokeverse.arena.presentation

import org.example.pokeverse.arena.data.Action

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