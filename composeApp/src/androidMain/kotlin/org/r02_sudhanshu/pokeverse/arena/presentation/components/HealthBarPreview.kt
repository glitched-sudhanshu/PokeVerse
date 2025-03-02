package org.r02_sudhanshu.pokeverse.arena.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.r02_sudhanshu.pokeverse.arena.data.Direction

@Preview
@Composable
private fun HealthBarPreview() {
    var progression by remember { mutableFloatStateOf(1f) }
    LaunchedEffect(Unit) {
        while (progression > 0) {
            delay(1000)
            progression -= 0.1f
        }
    }
    HealthBar(
        healthPercentage = { progression },
        direction = Direction.RTL,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    )
}