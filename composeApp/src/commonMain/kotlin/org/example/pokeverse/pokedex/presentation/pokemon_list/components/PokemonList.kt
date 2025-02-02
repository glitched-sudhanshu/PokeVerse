package org.example.pokeverse.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.example.pokeverse.pokedex.domain.model.Pokemon

@Composable
fun PokemonList(
    pokemons: List<Pokemon>,
    errorMessage: String,
    onClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyGridState
) {
    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
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
        items(pokemons, key = { it.id }) { pokemon ->
            PokemonListItem(
                modifier = modifier.widthIn(400.dp).fillMaxWidth(),
                pokemon = pokemon,
                onClick = onClick,
            )
        }
    }
}