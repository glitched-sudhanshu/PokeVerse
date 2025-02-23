package org.example.pokeverse.arena.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.example.pokeverse.arena.data.Direction
import org.example.pokeverse.core.presentation.ImageWithLoader
import org.jetbrains.compose.resources.painterResource
import pokeverse.composeapp.generated.resources.Res
import pokeverse.composeapp.generated.resources.open_pokeball

@Composable
fun AttackAnimation(
    modifier: Modifier = Modifier,
    direction: Direction,
    image: String,
    onAnimationEnd: () -> Unit,
) {
    val animatableX =
        remember {
            Animatable(
                if (direction == Direction.LTR) {
                    0f
                } else {
                    screenWidth
                },
            )
        }

    LaunchedEffect(Unit) {
        animatableX.animateTo(
            targetValue = if (direction == Direction.LTR) screenWidth else 0f,
            animationSpec =
            tween(
                durationMillis = 1500,
                easing = LinearEasing,
            ),
        )
        onAnimationEnd()
    }

    Box(
        modifier =
        modifier
            .fillMaxSize(),
    ) {
        ImageWithLoader(
            modifier = Modifier
                .graphicsLayer {
                    translationX =
                        animatableX.value + if (direction == Direction.LTR) (-155).dp.toPx() else 60.dp.toPx()
                },
            model = image,
            fallbackPainter = painterResource(Res.drawable.open_pokeball),
            contentDescription = null,
            loadingUi = null
        )
    }
}

expect val screenWidth: Float