package org.r02_sudhanshu.pokeverse.arena.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.r02_sudhanshu.pokeverse.app.ArenaRoute
import org.r02_sudhanshu.pokeverse.arena.data.Action

class ArenaViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _ground = savedStateHandle.toRoute<ArenaRoute.BattleGround>().ground
    private val _firstPlayer = savedStateHandle.toRoute<ArenaRoute.BattleGround>().firstPlayer
    private val _secondPlayer = savedStateHandle.toRoute<ArenaRoute.BattleGround>().secondPlayer

    private val actionMutex = Mutex()

    private val _arenaState = MutableStateFlow(
        ArenaState(
            ground = _ground,
            firstPlayer = _firstPlayer,
            secondPlayer = _secondPlayer
        )
    )
    val arenaState = _arenaState.asStateFlow()

    fun onAction(arenaAction: ArenaAction) {
        when (arenaAction) {
            is ArenaAction.ChooseGround -> {
                _arenaState.update {
                    it.copy(ground = arenaAction.ground)
                }
            }

            is ArenaAction.EnqueueFirstPlayerAction -> {
                if (_arenaState.value.gameOver) {
                    return
                }
                viewModelScope.launch {
                    actionMutex.withLock {
                        when (val action = arenaAction.action) {
                            is Action.Attack -> {
                                _arenaState.update {
                                    val firstPlayerActions =
                                        it.firstPlayerActions.toMutableList()
                                    firstPlayerActions.add(action)
                                    it.copy(
                                        firstPlayerActions = firstPlayerActions,
                                        firstPlayerHasSpecialAttack = if (action is Action.SpecialAttack) false else it.firstPlayerHasSpecialAttack,
                                    )
                                }
                            }

                            is Action.Defence -> {

                            }

                            is Action.Heal -> {

                            }
                        }
                    }
                }
            }

            is ArenaAction.EnqueueSecondPlayerAction -> {
                if (_arenaState.value.gameOver) {
                    return
                }
                viewModelScope.launch {
                    actionMutex.withLock {
                        when (val action = arenaAction.action) {
                            is Action.Attack -> {
                                _arenaState.update {
                                    val secondPlayerActions =
                                        it.secondPlayerActions.toMutableList()
                                    secondPlayerActions.add(action)
                                    it.copy(
                                        secondPlayerActions = secondPlayerActions,
                                        secondPlayerHasSpecialAttack = if (action is Action.SpecialAttack) false else it.secondPlayerHasSpecialAttack,
                                    )
                                }
                            }

                            is Action.Defence -> {

                            }

                            is Action.Heal -> {

                            }
                        }
                    }
                }
            }

            is ArenaAction.RemoveFirstPlayerAction -> {
                viewModelScope.launch {
                    actionMutex.withLock {
                        _arenaState.update {
                            val firstPlayerActions =
                                it.firstPlayerActions.toMutableList()
                            firstPlayerActions.remove(arenaAction.action)
                            val secondPlayerHealth =
                                if (arenaAction.action is Action.Attack && it.firstPlayerHealth > 0) {
                                    it.secondPlayerHealth - arenaAction.action.damage
                                } else {
                                    it.secondPlayerHealth
                                }
                            it.copy(
                                firstPlayerActions = firstPlayerActions,
                                secondPlayerHealth = secondPlayerHealth,
                                secondPlayerHasSpecialAttack = secondPlayerHealth < 50,
                                gameOver = secondPlayerHealth <= 0 || it.firstPlayerHealth <= 0
                            )
                        }
                    }
                }
            }

            is ArenaAction.RemoveSecondPlayerAction -> {
                viewModelScope.launch {
                    actionMutex.withLock {
                        _arenaState.update {
                            val secondPlayerActions =
                                it.secondPlayerActions.toMutableList()
                            secondPlayerActions.remove(arenaAction.action)
                            val firstPlayerHealth =
                                if (arenaAction.action is Action.Attack && it.secondPlayerHealth > 0) {
                                    it.firstPlayerHealth - arenaAction.action.damage
                                } else {
                                    it.firstPlayerHealth
                                }
                            it.copy(
                                secondPlayerActions = secondPlayerActions,
                                firstPlayerHealth = firstPlayerHealth,
                                firstPlayerHasSpecialAttack = firstPlayerHealth < 50,
                                gameOver = firstPlayerHealth <= 0 || it.secondPlayerHealth <= 0
                            )
                        }
                    }
                }
            }

            is ArenaAction.SelectPokemons -> {
                _arenaState.update {
                    it.copy(
                        firstPlayer = arenaAction.firstPlayer,
                        secondPlayer = arenaAction.secondPlayer
                    )
                }
            }
        }
    }
}
