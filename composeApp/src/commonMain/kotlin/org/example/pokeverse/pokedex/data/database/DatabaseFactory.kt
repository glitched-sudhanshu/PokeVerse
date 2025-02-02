package org.example.pokeverse.pokedex.data.database

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<FavoritePokemonDatabase>
}