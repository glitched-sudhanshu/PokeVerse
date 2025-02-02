package org.example.pokeverse.pokedex.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.example.pokeverse.pokedex.data.database.entities.PokemonEntity


@Dao
interface FavoritePokemonDao {

    @Upsert
    suspend fun upsert(pokemon: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity")
    fun getFavoritePokemons(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM PokemonEntity WHERE id = :id")
    suspend fun getFavoritePokemon(id: Int): PokemonEntity?

    @Query("DELETE FROM PokemonEntity WHERE id = :id")
    suspend fun deleteFavoritePokemon(id: Int)

}