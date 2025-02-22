package org.example.pokeverse.arena.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import org.example.pokeverse.core.presentation.ImageWithLoader
import org.example.pokeverse.core.presentation.clickableSingle
import org.example.pokeverse.core.presentation.currentTime
import org.jetbrains.compose.resources.painterResource
import pokeverse.composeapp.generated.resources.Res
import pokeverse.composeapp.generated.resources.fallback_ground
import pokeverse.composeapp.generated.resources.pokeball_loading

@Composable
fun RootBattleGroundScreen(
    modifier: Modifier = Modifier,
    viewModel: ArenaViewModel,
) {
    DisposableEffect(Unit) {
        setScreenOrientationLandscape()
        onDispose {
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
    Box(modifier = modifier.fillMaxSize()) {
        ImageWithLoader(
            model = arenaState.ground,
            contentDescription = "ground-${arenaState.ground}",
            loadingUi = null,
            modifier = Modifier.fillMaxSize(),
            fallbackPainter = painterResource(Res.drawable.fallback_ground)
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
                                    image = "https://png.pngtree.com/png-vector/20231119/ourmid/pngtree-water-wave-isolated-on-transparent-background-png-image_10668832.png",
                                    metaData = Action.MetaData(
                                        primaryColor = 0xFF03ADFC,
                                        secondaryColor = 0xFF034AFC,
                                    ),
                                    damage = 20f,
                                    title = "Water Splash"
                                )
                            )
                        )
                    }
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .clickableSingle(enabled = !arenaState.gameOver) {
                        onAction(
                            ArenaAction.EnqueueSecondPlayerAction(
                                Action.Attack(
                                    timestamp = currentTime.toString(),
                                    image = "https://static.vecteezy.com/system/resources/previews/023/364/242/original/yellow-ball-lightning-abstract-electric-lightning-strike-light-flash-thunder-spark-png.png",
                                    metaData = Action.MetaData(
                                        primaryColor = 0xFFFCA503,
                                        secondaryColor = 0xFFED5407,
                                    ),
                                    damage = 20f,
                                    title = "Electro Ball"
                                )
                            )
                        )
                    }
            )
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
                                image = "https://static.vecteezy.com/system/resources/previews/041/734/645/non_2x/ai-generated-water-splash-water-splash-free-png.png",
                                metaData = Action.MetaData(
                                    primaryColor = 0xFF03ADFC,
                                    secondaryColor = 0xFF034AFC,
                                ),
                                damage = 45f,
                                title = "Water Swirl"
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
                            fallbackPainter = painterResource(Res.drawable.pokeball_loading),
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
                                image = "https://png.pngtree.com/png-vector/20240202/ourmid/pngtree-yellow-ball-lightning-abstract-electric-lightning-strike-light-flash-thunder-spark-png-image_11531017.png",
                                metaData = Action.MetaData(
                                    primaryColor = 0xFFFCA503,
                                    secondaryColor = 0xFFED5407,
                                ),
                                damage = 45f,
                                title = "Thunder Bolt"
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
                            fallbackPainter = painterResource(Res.drawable.pokeball_loading),
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