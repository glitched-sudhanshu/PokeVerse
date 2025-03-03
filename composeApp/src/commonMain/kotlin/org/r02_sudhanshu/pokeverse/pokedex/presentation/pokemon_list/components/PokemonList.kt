package org.r02_sudhanshu.pokeverse.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.r02_sudhanshu.pokeverse.pokedex.domain.model.Pokemon
import org.jetbrains.compose.resources.painterResource
import pokeverse.composeapp.generated.resources.Res
import pokeverse.composeapp.generated.resources.pokeball_loading

@Composable
fun PokemonList(
    pokemons: List<Pokemon>,
    errorMessage: String,
    onClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyGridState,
    loadNextPagePokemons: () -> Unit,
    isNextPageLoading: Boolean = false
) {
    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = if (pokemons.isEmpty()) Arrangement.spacedBy(
            12.dp,
            Alignment.CenterVertically
        ) else Arrangement.spacedBy(12.dp)
    ) {
        if (pokemons.isEmpty()) {
            item(span = {
                GridItemSpan(2)
            }) {
                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        itemsIndexed(
            pokemons,
            key = { index, pokemon -> "$index${pokemon.id}" }) { index, pokemon ->
            if (index >= pokemons.size - 1) {
                loadNextPagePokemons()
            }
            PokemonListItem(
                modifier = modifier.widthIn(400.dp).fillMaxWidth().animateItem(),
                pokemon = pokemon,
                onClick = onClick,
            )
        }
        if (isNextPageLoading) {
            item(span = {
                GridItemSpan(2)
            }) {
                Image(
                    painter = painterResource(Res.drawable.pokeball_loading),
                    contentDescription = "page-loader",
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}