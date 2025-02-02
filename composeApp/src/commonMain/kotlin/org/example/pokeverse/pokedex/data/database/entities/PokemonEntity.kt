package org.example.pokeverse.pokedex.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.example.pokeverse.pokedex.domain.model.Ability
import org.example.pokeverse.pokedex.domain.model.Move
import org.example.pokeverse.pokedex.domain.model.Stat
import org.example.pokeverse.pokedex.domain.model.Type

@Entity
data class PokemonEntity(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val soundUrl: String?,
    val height: Int,
    @PrimaryKey(autoGenerate = false) val id: Int,
    val moves: List<Move>,
    val name: String,
    val species: String,
    val imageUrl: String?,
    val gifUrl: String?,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)
