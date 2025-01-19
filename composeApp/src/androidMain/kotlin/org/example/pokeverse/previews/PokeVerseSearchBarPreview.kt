package org.example.pokeverse.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.example.pokeverse.pokedex.presentation.pokemon_list.components.PokeVerseSearchBar

@Preview
@Composable
private fun PokeVerseSearchBarPreview() {
    MaterialTheme {
        PokeVerseSearchBar(
            searchQuery = "Pika",
            onSearchQueryChange = {},
            onImeSearch = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}