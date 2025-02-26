package org.example.pokeverse.arena.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import org.example.pokeverse.app.LocalNavController
import org.example.pokeverse.arena.data.Action
import org.example.pokeverse.arena.data.Direction
import org.example.pokeverse.arena.presentation.components.ActionButton
import org.example.pokeverse.arena.presentation.components.AttackAnimation
import org.example.pokeverse.arena.presentation.components.ExplodePokemon
import org.example.pokeverse.arena.presentation.components.HealthBar
import org.example.pokeverse.arena.presentation.utils.electroBallImage
import org.example.pokeverse.arena.presentation.utils.pikachuMetaData
import org.example.pokeverse.arena.presentation.utils.pikachuNormalAttackDamage
import org.example.pokeverse.arena.presentation.utils.pikachuNormalAttackTitle
import org.example.pokeverse.arena.presentation.utils.pikachuSpecialAttackDamage
import org.example.pokeverse.arena.presentation.utils.pikachuSpecialAttackTitle
import org.example.pokeverse.arena.presentation.utils.squirtleMetaData
import org.example.pokeverse.arena.presentation.utils.squirtleNormalAttackDamage
import org.example.pokeverse.arena.presentation.utils.squirtleNormalAttackTitle
import org.example.pokeverse.arena.presentation.utils.squirtleSpecialAttackDamage
import org.example.pokeverse.arena.presentation.utils.squirtleSpecialAttackTitle
import org.example.pokeverse.arena.presentation.utils.thunderBoltImage
import org.example.pokeverse.arena.presentation.utils.waterSplashImage
import org.example.pokeverse.arena.presentation.utils.waterSwirlImage
import org.example.pokeverse.core.presentation.AudioViewModel
import org.example.pokeverse.core.presentation.ImageWithLoader
import org.example.pokeverse.core.presentation.clickableSingle
import org.example.pokeverse.core.presentation.currentTime
import pokeverse.composeapp.generated.resources.Res
import pokeverse.composeapp.generated.resources.fallback_ground
import pokeverse.composeapp.generated.resources.pokeball_loading

@Composable
fun RootBattleGroundScreen(
    modifier: Modifier = Modifier,
    viewModel: ArenaViewModel,
    audioViewModel: AudioViewModel
) {
    DisposableEffect(Unit) {
        audioViewModel.play(
            "https://ia801408.us.archive.org/19/items/pokemon-battle-sound/pokemon-battle.mp3",
            loop = true
        )
        setScreenOrientationLandscape()
        onDispose {
            audioViewModel.stop()
            setScreenOrientationPortrait()
        }
    }

    val arenaState by viewModel.arenaState.collectAsStateWithLifecycle()
    val navController = LocalNavController.current
    LaunchedEffect(arenaState.gameOver) {
        if (arenaState.gameOver) {
            delay(3000)
            navController.popBackStack()
        }
    }

    BattleGroundScreen(
        modifier = modifier,
        arenaState = arenaState,
        onAction = {
            viewModel.onAction(it)
        }
    )
}

@Composable
fun BattleGroundScreen(
    modifier: Modifier = Modifier,
    arenaState: ArenaState,
    onAction: (ArenaAction) -> Unit
) {
    LaunchedEffect(arenaState) {
        println("dekh dekh $arenaState")
    }
    Box(modifier = modifier.fillMaxSize()) {
        ImageWithLoader(
            model = arenaState.ground,
            contentDescription = "ground-${arenaState.ground}",
            loadingUi = null,
            modifier = Modifier.fillMaxSize(),
            fallbackResource = Res.drawable.fallback_ground
        )
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .clickableSingle(enabled = !arenaState.gameOver) {
                        onAction(
                            ArenaAction.EnqueueFirstPlayerAction(
                                Action.Attack(
                                    timestamp = currentTime.toString(),
                                    image = waterSplashImage,
                                    metaData = squirtleMetaData,
                                    damage = squirtleNormalAttackDamage,
                                    title = squirtleNormalAttackTitle
                                )
                            )
                        )
                    }
            ) {
                val firstPlayerHealth by derivedStateOf { arenaState.firstPlayerHealth / 100f }
                HealthBar(
                    healthPercentage = { firstPlayerHealth },
                    direction = Direction.LTR,
                    modifier = Modifier.padding(24.dp).fillMaxWidth().height(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .clickableSingle(enabled = !arenaState.gameOver) {
                        onAction(
                            ArenaAction.EnqueueSecondPlayerAction(
                                Action.Attack(
                                    timestamp = currentTime.toString(),
                                    image = electroBallImage,
                                    metaData = pikachuMetaData,
                                    damage = pikachuNormalAttackDamage,
                                    title = pikachuNormalAttackTitle
                                )
                            )
                        )
                    }
            ) {
                val secondPlayerHealth by derivedStateOf { arenaState.secondPlayerHealth / 100f }
                HealthBar(
                    healthPercentage = { secondPlayerHealth },
                    direction = Direction.RTL,
                    modifier = Modifier.padding(24.dp).fillMaxWidth().height(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().padding(24.dp)) {
            AnimatedContent(
                targetState = arenaState.firstPlayerHealth > 0,
                label = "",
                modifier = Modifier.align(Alignment.BottomStart),
            ) { health ->
                if (health) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        ActionButton(
                            action = Action.SpecialAttack(
                                timestamp = currentTime.toString(),
                                image = waterSwirlImage,
                                metaData = squirtleMetaData,
                                damage = squirtleSpecialAttackDamage,
                                title = squirtleSpecialAttackTitle
                            ),
                            onPerform = {
                                onAction(
                                    ArenaAction.EnqueueFirstPlayerAction(
                                        action = it
                                    )
                                )
                            },
                            modifier = Modifier.size(100.dp),
                            isActive = arenaState.firstPlayerHasSpecialAttack,
                        )
                        ImageWithLoader(
                            model = arenaState.firstPlayer,
                            contentDescription = "first-player",
                            contentScale = ContentScale.Fit,
                            fallbackResource = Res.drawable.pokeball_loading,
                            modifier = Modifier
                                .size(width = 100.dp, height = 140.dp)
                                .graphicsLayer { rotationY = -180f },
                            loadingUi = null,
                        )
                    }
                } else {
                    ExplodePokemon()
                }
            }
            AnimatedContent(
                targetState = arenaState.secondPlayerHealth > 0,
                label = "",
                modifier = Modifier.align(Alignment.BottomEnd),
            ) { health ->
                if (health) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        ActionButton(
                            action = Action.SpecialAttack(
                                timestamp = currentTime.toString(),
                                image = thunderBoltImage,
                                metaData = pikachuMetaData,
                                damage = pikachuSpecialAttackDamage,
                                title = pikachuSpecialAttackTitle
                            ),
                            onPerform = {
                                onAction(ArenaAction.EnqueueSecondPlayerAction(it))
                            },
                            modifier = Modifier.size(100.dp),
                            isActive = arenaState.secondPlayerHasSpecialAttack,
                        )
                        ImageWithLoader(
                            model = arenaState.secondPlayer,
                            contentDescription = "second-player",
                            fallbackResource = Res.drawable.pokeball_loading,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(width = 100.dp, height = 140.dp),
                            loadingUi = null,
                        )
                    }
                } else {
                    ExplodePokemon()
                }
            }
            arenaState.firstPlayerActions.forEach { action ->
                key(action.timestamp) {
                    when (action) {
                        is Action.Attack -> {
                            AttackAnimation(
                                modifier = Modifier
                                    .padding(bottom = 12.dp)
                                    .align(Alignment.BottomStart)
                                    .size(60.dp),
                                direction = Direction.LTR,
                                image = action.image,
                                onAnimationEnd = {
                                    onAction(ArenaAction.RemoveFirstPlayerAction(action))
                                },
                            )
                        }

                        is Action.Defence -> {}
                        is Action.Heal -> {}
                    }
                }
            }
            arenaState.secondPlayerActions.forEach { action ->
                key(action.timestamp) {
                    when (action) {
                        is Action.Attack -> {
                            AttackAnimation(
                                modifier =
                                Modifier
                                    .padding(bottom = 12.dp)
                                    .align(Alignment.BottomStart)
                                    .size(60.dp),
                                direction = Direction.RTL,
                                image = action.image,
                                onAnimationEnd = {
                                    onAction(ArenaAction.RemoveSecondPlayerAction(action))
                                },
                            )
                        }

                        is Action.Defence -> {}
                        is Action.Heal -> {}
                    }
                }
            }
        }

        if (arenaState.gameOver) {
            Box(
                modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(.5f)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Game Over",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                )
            }
        }
    }
}

expect fun setScreenOrientationLandscape()

expect fun setScreenOrientationPortrait()