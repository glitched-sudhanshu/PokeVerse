package org.example.pokeverse.pokedex.presentation.pokemon_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.pokeverse.pokedex.domain.Pokemon

@Composable
fun PokemonList(
    pokemons: List<Pokemon>,
    onClick: (Pokemon) -> Unit,
    onLikeClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
    listState: LazyGridState = rememberLazyGridState()
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
        items(pokemons, key = { it.id }) { pokemon ->
            PokemonListItem(
                modifier = modifier.widthIn(400.dp).fillMaxWidth(),
                pokemon = Pokemon(
                    types = pokemon.types,
                    name = pokemon.name,
                    id = pokemon.id,
                    height = pokemon.height,
                    imageUrl = pokemon.imageUrl,
                    moves = pokemon.moves,
                    weight = pokemon.weight,
                    abilities = pokemon.abilities,
                    baseExperience = pokemon.baseExperience,
                ),
                onClick = onClick,
                onLikeClick = onLikeClick
            )
        }
    }
}