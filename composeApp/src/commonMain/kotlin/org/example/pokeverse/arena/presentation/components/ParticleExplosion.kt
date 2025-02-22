package org.example.pokeverse.arena.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.math.pow
import kotlin.random.Random

@Composable
fun ExplodePokemon() {
    var animationOver by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(100)
        animationOver = true
    }
    val progress by animateFloatAsState(
        targetValue = if (animationOver) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1500,
            easing = LinearEasing
        )
    )
    Explosion(progress)
}

private val sizeDp = 200.dp
private val sizePx = sizeDp.toPx()
private val sizePxHalf = sizePx / 2
private val particles = List(150) {
    Particle(
        color = Color(
            listOf(
                0xFFEA4335,
                0xff4285f4,
                0xfffbbc05,
                0xff34a853
            ).random()
        ),
        startXPosition = sizePxHalf.toInt(),
        startYPosition = sizePxHalf.toInt(),
        maxHorizontalDisplacement = sizePx * randomInRange(-0.9f, 0.9f),
        maxVerticalDisplacement = sizePx * randomInRange(0.2f, 0.38f)
    )
}


@Composable
fun Explosion(progress: Float) {
    particles.forEach { it.updateProgress(progress) }

    Canvas(
        modifier = Modifier
            .size(sizeDp)
    ) {
        particles.forEach { particle ->
            drawCircle(
                alpha = particle.alpha,
                color = particle.color,
                radius = particle.currentRadius,
                center = Offset(particle.currentXPosition, particle.currentYPosition),
            )
        }
    }
}

class Particle(
    val color: Color,
    val startXPosition: Int,
    val startYPosition: Int,
    val maxHorizontalDisplacement: Float,
    val maxVerticalDisplacement: Float
) {
    private val velocity = 4 * maxVerticalDisplacement
    private val acceleration = -2 * velocity
    var currentXPosition = 0f
    var currentYPosition = 0f

    private var visibilityThresholdLow = randomInRange(0f, 0.14f)
    private var visibilityThresholdHigh = randomInRange(0f, 0.4f)

    private val initialXDisplacement = 10.dp.toPx() * randomInRange(-1f, 1f)
    private val initialYDisplacement = 10.dp.toPx() * randomInRange(-1f, 1f)

    var alpha = 0f
    var currentRadius = 0f
    private val startRadius = 2.dp.toPx()
    private val endRadius = if (randomBoolean(trueProbabilityPercentage = 20)) {
        randomInRange(startRadius, 7.dp.toPx())
    } else {
        randomInRange(1.5.dp.toPx(), startRadius)
    }

    fun updateProgress(explosionProgress: Float) {
        val trajectoryProgress =
            if (explosionProgress < visibilityThresholdLow || (explosionProgress > (1 - visibilityThresholdHigh))) {
                alpha = 0f; return
            } else (explosionProgress - visibilityThresholdLow).mapInRange(
                0f,
                1f - visibilityThresholdHigh - visibilityThresholdLow,
                0f,
                1f
            )
        alpha = if (trajectoryProgress < 0.7f) 1f else (trajectoryProgress - 0.7f).mapInRange(
            0f,
            0.3f,
            1f,
            0f
        )
        currentRadius = startRadius + (endRadius - startRadius) * trajectoryProgress
        val currentTime = trajectoryProgress.mapInRange(0f, 1f, 0f, 1.4f)
        val verticalDisplacement =
            (currentTime * velocity + 0.5 * acceleration * currentTime.toDouble()
                .pow(2.0)).toFloat()
        currentYPosition = startXPosition + initialXDisplacement - verticalDisplacement
        currentXPosition =
            startYPosition + initialYDisplacement + maxHorizontalDisplacement * trajectoryProgress
    }
}

private fun Float.mapInRange(inMin: Float, inMax: Float, outMin: Float, outMax: Float): Float {
    return outMin + (((this - inMin) / (inMax - inMin)) * (outMax - outMin))
}

fun Int.dpToPx() = toFloat().dpToPx()
private fun Dp.toPx() = value.dpToPx()

expect fun Float.dpToPx(): Float

private fun Float.randomTillZero() = this * Random.nextFloat()
private fun randomInRange(min: Float, max: Float) = min + (max - min).randomTillZero()
private fun randomBoolean(trueProbabilityPercentage: Int) =
    Random.nextFloat() < trueProbabilityPercentage / 100f