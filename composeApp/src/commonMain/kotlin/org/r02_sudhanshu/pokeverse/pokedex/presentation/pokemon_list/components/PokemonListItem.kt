package org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import getPokemonTypeColor
import org.r02_sudhanshu.pokeverse.core.presentation.DesertWhite
import org.r02_sudhanshu.pokeverse.core.presentation.clickableSingle
import org.r02_sudhanshu.pokeverse.pokedex.domain.model.Pokemon
import org.jetbrains.compose.resources.painterResource
import pokeverse.composeapp.generated.resources.Res
import pokeverse.composeapp.generated.resources.ic_pokeball
import pokeverse.composeapp.generated.resources.open_pokeball

@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    onClick: (Pokemon) -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(32.dp),
        modifier = modifier.clip(RoundedCornerShape(32.dp)).clickableSingle { onClick(pokemon) },
        color = getPokemonTypeColor(
            pokemon.types.getOrNull(0)?.pokemonType
        )
            ?: Color.Black.copy(0.3f),
    ) {
        Box(modifier = Modifier.wrapContentSize()) {
            Image(
                painter = painterResource(Res.drawable.ic_pokeball),
                contentDescription = "pokeball",
                colorFilter = ColorFilter.tint(Color.White.copy(.4f)),
                modifier = Modifier.size(164.dp).align(Alignment.BottomEnd)
                    .offset(x = 48.dp, y = 48.dp),
            )

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = pokemon.name.capitalize(Locale.current),
                    maxLines = 1,
                    color = DesertWhite,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.basicMarquee().padding(16.dp)
                )
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 16.dp, end = 8.dp, bottom = 4.dp)
                ) {
                    var imageLoadResult by remember {
                        mutableStateOf<Result<Painter>?>(null)
                    }
                    val painter = rememberAsyncImagePainter(model = pokemon.imageUrl, onSuccess = {
                        imageLoadResult =
                            if (it.painter.intrinsicSize.height > 1 && it.painter.intrinsicSize.width > 1) {
                                Result.success(it.painter)
                            } else {
                                Result.failure(Throwable("Invalid image size."))
                            }
                    }, onError = {
                        it.result.throwable.printStackTrace()
                        imageLoadResult = Result.failure(it.result.throwable)
                    })

                    when (val result = imageLoadResult) {
                        null -> CircularProgressIndicator()
                        else -> {
                            Image(
                                painter = if (result.isSuccess) painter else painterResource(Res.drawable.open_pokeball),
                                contentDescription = pokemon.name,
                                contentScale = if (result.isSuccess) ContentScale.Crop else ContentScale.Fit,
                                modifier = Modifier
                                    .sizeIn(
                                        minWidth = 120.dp,
                                        maxWidth = 400.dp,
                                        minHeight = 120.dp,
                                        maxHeight = 400.dp
                                    )
                                    .size(150.dp)
                                    .align(Alignment.BottomEnd).padding(start = 12.dp),
                            )
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        pokemon.types.forEach { type ->
                            Text(
                                text = type.name.capitalize(Locale.current),
                                color = DesertWhite,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.clip(RoundedCornerShape(100))
                                    .background(Color.White.copy(.2f))
                                    .padding(vertical = 2.dp, horizontal = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
