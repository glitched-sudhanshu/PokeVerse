package org.r02_sudhanshu.pokeverse.pokedex.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    val abilities: List<AbilityEntity>,
    val baseExperience: Int,
    val soundUrl: String?,
    val height: Int,
    @PrimaryKey(autoGenerate = false) val id: Int,
    val moves: List<MoveEntity>,
    val name: String,
    val species: String,
    val imageUrl: String?,
    val gifUrl: String?,
    val stats: List<StatEntity>,
    val types: List<TypeEntity>,
    val weight: Int
)
