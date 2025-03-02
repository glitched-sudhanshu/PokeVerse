package org.r02_sudhanshu.pokeverse.arena.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.r02_sudhanshu.pokeverse.arena.data.Direction

@Composable
fun HealthBar(
    healthPercentage: () -> Float,
    direction: Direction,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedHealth by animateFloatAsState(
        targetValue = healthPercentage(),
        animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
    )

    val gradientOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(modifier = modifier) {
        val width = size.width * animatedHealth
        val height = size.height
        drawRect(
            color = Color(0xFF6DD400).copy(alpha = 0.2f),
            size = Size(size.width, height)
        )
        val gradient = Brush.linearGradient(
            colors = listOf(
                Color(0xFF6DD400),
                Color(0xFF4CAF50),
                Color(0xFF6DD400)
            ),
            start = Offset(gradientOffset, 0f),
            end = Offset(gradientOffset + size.width, 0f)
        )

        if (direction == Direction.LTR) {
            drawRect(brush = gradient, size = Size(width, height))
        } else {
            drawRect(
                brush = gradient,
                topLeft = Offset(size.width - width, 0f),
                size = Size(width, height)
            )
        }
    }
}

