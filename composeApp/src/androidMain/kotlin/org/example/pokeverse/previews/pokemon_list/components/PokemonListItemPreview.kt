package org.example.pokeverse.previews.pokemon_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.pokeverse.pokedex.domain.Ability
import org.example.pokeverse.pokedex.domain.Move
import org.example.pokeverse.pokedex.domain.Pokemon
import org.example.pokeverse.pokedex.domain.PokemonType
import org.example.pokeverse.pokedex.presentation.pokemon_list.components.PokemonListItem

@Preview
@Composable
private fun PokemonListItemPreview() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        PokemonListItem(
            modifier = Modifier.weight(1f),
            pokemon =
            Pokemon(
                types = listOf(PokemonType.Fairy),
                name = "clefairy",
                id = "12",
                height = 12,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                moves = listOf(Move("pound")),
                weight = 13,
                abilities = listOf(Ability("friend-guard")),
                baseExperience = 124,
            ),
            onClick = {}
        )
        PokemonListItem(
            modifier = Modifier.weight(1f),
            pokemon =
            Pokemon(
                types = listOf(PokemonType.Fairy),
                name = "clefairasfsafasfasfy",
                id = "12",
                height = 12,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png",
                moves = listOf(Move("pound")),
                weight = 13,
                abilities = listOf(Ability("friend-guard")),
                baseExperience = 124,
            ),
            onClick = {}
        )
    }
}
