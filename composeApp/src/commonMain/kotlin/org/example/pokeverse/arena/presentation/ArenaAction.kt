package org.example.pokeverse.arena.presentation

import org.example.pokeverse.arena.data.Action

sealed interface ArenaAction {
    data class ChooseGround(val ground: String) : ArenaAction
    data class SelectPokemons(val firstPlayer: String, val secondPlayer: String) : ArenaAction
    data class EnqueueFirstPlayerAction(val action: Action) : ArenaAction
    data class EnqueueSecondPlayerAction(val action: Action) : ArenaAction
    data class RemoveFirstPlayerAction(val action: Action) : ArenaAction
    data class RemoveSecondPlayerAction(val action: Action) : ArenaAction
}