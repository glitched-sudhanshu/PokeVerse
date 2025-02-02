package org.example.pokeverse.pokedex.data.database

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object PokemonDatabaseConstructor: RoomDatabaseConstructor<FavoritePokemonDatabase> {
    override fun initialize(): FavoritePokemonDatabase
}