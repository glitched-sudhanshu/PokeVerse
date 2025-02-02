package org.example.pokeverse.pokedex.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.example.pokeverse.pokedex.data.database.entities.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1
)
@TypeConverters(
    PokemonTypeConverters::class
)
@ConstructedBy(PokemonDatabaseConstructor::class)
abstract class FavoritePokemonDatabase : RoomDatabase() {
    abstract val favoritePokemonDao: FavoritePokemonDao

    companion object {
        const val DB_NAME = "PokeVerse.db"
    }
}