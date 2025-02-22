package org.example.pokeverse.arena.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.example.pokeverse.arena.data.Action
import org.example.pokeverse.core.presentation.ImageWithLoader
import org.example.pokeverse.core.presentation.clickableSingle
import org.jetbrains.compose.resources.painterResource
import pokeverse.composeapp.generated.resources.Res
import pokeverse.composeapp.generated.resources.pokeball_loading

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    action: Action,
    isActive: Boolean,
    onPerform: (Action) -> Unit
) {
    val moveActivationAnimation =
        updateTransitionData(
            isActive = isActive,
            listOf(Color(action.metaData.primaryColor), Color(action.metaData.secondaryColor)),
        )
    val infiniteTransition = rememberInfiniteTransition()
    val angle by
    infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec =
        infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "",
    )

    val scale by
    infiniteTransition
        .animateFloat(
            initialValue = 1f,
            targetValue = 1.1f,
            animationSpec =
            infiniteRepeatable(
                animation = tween(700, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
            label = "",
        )
    Box(modifier = modifier.clickableSingle(ripple = false) { if (isActive) onPerform(action) }) {
        ImageWithLoader(
            modifier = Modifier.padding(4.dp),
            model = action.image,
            loadingUi = null,
            fallbackPainter = painterResource(Res.drawable.pokeball_loading),
            contentDescription = action.title,
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix().apply {
                    setToSaturation(
                        moveActivationAnimation.grayScale,
                    )
                },
            ),
        )
        Canvas(
            modifier =
            Modifier
                .matchParentSize()
                .drawBehind {
                    val strokeWidth = 2.dp.toPx()
                    val radius =
                        size.minDimension / 2 - strokeWidth / 2
                    rotate(angle) {
                        drawCircle(
                            brush =
                            Brush.horizontalGradient(
                                colors =
                                listOf(
                                    moveActivationAnimation.primaryColor,
                                    moveActivationAnimation.secondaryColor,
                                ),
                            ),
                            radius = radius,
                            style = Stroke(width = strokeWidth),
                        )
                    }
                }.graphicsLayer {
                    alpha = 0.99f
                    if (isActive) {
                        scaleX = scale
                        scaleY = scale
                    }
                },
        ) {}
    }
}

private class TransitionData(
    primaryColor: State<Color>,
    secondaryColor: State<Color>,
    grayScale: State<Float>,
) {
    val primaryColor by primaryColor
    val secondaryColor by secondaryColor
    val grayScale by grayScale
}

@Composable
private fun updateTransitionData(
    isActive: Boolean,
    colors: List<Color>,
): TransitionData {
    val transition = updateTransition(isActive, label = "move state")
    val primaryColor =
        transition.animateColor(label = "primary color") { state ->
            when (state) {
                false -> Color.Gray
                true -> colors[0]
            }
        }
    val secondaryColor =
        transition.animateColor(label = "secondary color") { state ->
            when (state) {
                false -> Color.Gray
                true -> colors[1]
            }
        }
    val grayScale =
        transition.animateFloat(label = "alpha") { state ->
            when (state) {
                false -> 0f
                true -> 1f
            }
        }
    return remember(transition) {
        TransitionData(
            primaryColor = primaryColor,
            secondaryColor = secondaryColor,
            grayScale = grayScale,
        )
    }
}
