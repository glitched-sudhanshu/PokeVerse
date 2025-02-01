package org.example.pokeverse.previews.pokemon_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.pokeverse.pokedex.presentation.pokemon_details.testPokemon
import org.example.pokeverse.pokedex.presentation.pokemon_list.components.PokemonListItem

@Preview
@Composable
private fun PokemonListItemPreview() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        PokemonListItem(
            modifier = Modifier.weight(1f),
            pokemon = testPokemon,
            onClick = {}
        )
        PokemonListItem(
            modifier = Modifier.weight(1f),
            pokemon = testPokemon,
            onClick = {}
        )
    }
}
