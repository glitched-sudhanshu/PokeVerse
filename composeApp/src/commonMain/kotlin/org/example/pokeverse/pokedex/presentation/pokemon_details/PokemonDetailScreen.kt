package org.example.pokeverse.pokedex.presentation.pokemon_details

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import getPokemonTypeColor
import org.example.pokeverse.core.presentation.DesertWhite
import org.example.pokeverse.core.presentation.ImageWithLoader
import org.example.pokeverse.pokedex.domain.model.Pokemon
import org.example.pokeverse.pokedex.presentation.pokemon_details.components.PokemonStatItem
import org.jetbrains.compose.resources.painterResource
import pokeverse.composeapp.generated.resources.Res
import pokeverse.composeapp.generated.resources.ic_pokeball
import pokeverse.composeapp.generated.resources.open_pokeball

@Composable
fun PokemonDetailScreenRoot(
    viewModel: PokemonDetailViewModel,
    onBackClick: () -> Unit,
) {
    val state by viewModel.pokemonDetailsState.collectAsStateWithLifecycle()
    state.pokemon?.let { pokemon ->
        PokemonDetailScreen(
            pokemon = pokemon,
            onAction = {
                when (it) {
                    PokemonDetailAction.OnBackClick -> {
                        onBackClick()
                    }

                    else -> {

                    }
                }
            }
        )
    }
}

@Composable
fun PokemonDetailScreen(
    pokemon: Pokemon,
    onAction: (PokemonDetailAction) -> Unit
) {
    Scaffold { internalPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(internalPadding).background(
                getPokemonTypeColor(pokemon.types.getOrNull(0)?.pokemonType) ?: Color.Black.copy(
                    0.3f
                )
            ), verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(.4f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back",
                        modifier = Modifier
                            .padding(12.dp)
                            .size(32.dp)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(.5f))
                            .align(Alignment.TopStart)
                            .clickable {
                                onAction(PokemonDetailAction.OnBackClick)
                            },
                        colorFilter = ColorFilter.tint(Color.Black.copy(.8f))
                    )
                    val infiniteTransition = rememberInfiniteTransition()
                    val infiniteScale = infiniteTransition.animateFloat(
                        initialValue = .75f,
                        targetValue = 1.25f,
                        animationSpec = InfiniteRepeatableSpec(
                            animation = tween(
                                800,
                                delayMillis = 0,
                                easing = LinearEasing
                            ),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                    val infiniteRotate = infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = InfiniteRepeatableSpec(
                            animation = tween(
                                2400,
                                delayMillis = 0,
                                easing = LinearEasing
                            ),
                            repeatMode = RepeatMode.Restart
                        )
                    )
                    Image(
                        painter = painterResource(Res.drawable.ic_pokeball),
                        contentDescription = "bg-image",
                        colorFilter = ColorFilter.tint(
                            Color.White.copy(.3f)
                        ),
                        modifier = Modifier.matchParentSize().padding(vertical = 32.dp).rotate(30f)
                            .aspectRatio(1f, matchHeightConstraintsFirst = true).graphicsLayer {
                                scaleX = infiniteScale.value
                                scaleY = infiniteScale.value
                                rotationZ = infiniteRotate.value
                            }
                    )
                    var animatePokemonState by remember { mutableStateOf(true) }
                    val animatePokemon by animateFloatAsState(
                        targetValue = if (animatePokemonState) 0.75f else 1f,
                        animationSpec = tween(500)
                    )
                    LaunchedEffect(Unit) {
                        animatePokemonState = false
                    }
                    ImageWithLoader(
                        model = pokemon.imageUrl,
                        contentDescription = pokemon.name,
                        fallbackPainter = painterResource(Res.drawable.open_pokeball),
                        modifier = Modifier.matchParentSize().graphicsLayer {
                            scaleX = animatePokemon
                            scaleY = animatePokemon
                        }.padding(vertical = 32.dp)
                            .aspectRatio(1f, matchHeightConstraintsFirst = true)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.horizontalScroll(rememberScrollState())
                ) {
                    pokemon.types.forEach { type ->
                        Text(
                            text = type.name.capitalize(Locale.current),
                            color = DesertWhite,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.clip(RoundedCornerShape(100))
                                .background(Color.White.copy(.2f)).border(
                                    width = 1.dp,
                                    shape = RoundedCornerShape(100),
                                    color = Color.White
                                ).padding(vertical = 2.dp, horizontal = 8.dp)
                        )
                    }
                }
            }
            var animateSheetState by remember { mutableStateOf(true) }
            val animateSheet by animateFloatAsState(
                targetValue = if (animateSheetState) 400f else 0f, animationSpec = tween(500)
            )
            LaunchedEffect(Unit) {
                animateSheetState = false
            }
            Column(
                modifier = Modifier.graphicsLayer {
                    translationY = animateSheet
                }.clip(RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)).background(Color.White)
                    .padding(vertical = 12.dp, horizontal = 16.dp).fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Info     ",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier.drawBehind {
                            val strokeWidthPx = 1.dp.toPx()
                            val verticalOffset = size.height
                            drawLine(
                                color = Color.Black,
                                strokeWidth = strokeWidthPx,
                                start = Offset(0f, verticalOffset),
                                end = Offset(size.width, verticalOffset)
                            )
                        })
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PokemonStatItem(
                        label = "Weight",
                        value = "${pokemon.weight} hg",
                        modifier = Modifier.fillMaxWidth()
                    )
                    PokemonStatItem(
                        label = "Height",
                        value = "${pokemon.height} dm",
                        modifier = Modifier.fillMaxWidth()
                    )
                    PokemonStatItem(
                        label = "Weight",
                        value = "${pokemon.weight} hg",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
