package org.example.pokeverse.pokedex.data.mappers

import org.example.pokeverse.pokedex.data.database.entities.PokemonEntity
import org.example.pokeverse.pokedex.data.dto.AbilityDto
import org.example.pokeverse.pokedex.data.dto.MoveDto
import org.example.pokeverse.pokedex.data.dto.PokemonDto
import org.example.pokeverse.pokedex.data.dto.StatDto
import org.example.pokeverse.pokedex.data.dto.TypeDto
import org.example.pokeverse.pokedex.domain.model.Ability
import org.example.pokeverse.pokedex.domain.model.Move
import org.example.pokeverse.pokedex.domain.model.Pokemon
import org.example.pokeverse.pokedex.domain.model.Stat
import org.example.pokeverse.pokedex.domain.model.Type

fun AbilityDto.toAbility(): Ability {
    return Ability(ability = this.ability.name, slot = this.slot)
}

fun MoveDto.toMove() = Move(move = this.move.name)

fun StatDto.toStat() =
    Stat(stat = this.stat.name, baseStat = this.baseStat, effort = this.effort)

fun TypeDto.toType() = Type(name = this.type.name, slot = this.slot)

fun PokemonDto.toPokemon() = Pokemon(
    id = this.id,
    name = this.name,
    soundUrl = this.cries.legacy ?: this.cries.latest,
    species = this.species.name,
    height = this.height,
    weight = this.weight,
    moves = this.moves.map { it.toMove() },
    stats = this.stats.map { it.toStat() },
    baseExperience = this.baseExperience,
    types = this.types.map { it.toType() },
    abilities = this.abilities.map { it.toAbility() },
    imageUrl = this.sprites.frontDefault ?: this.sprites.frontShiny ?: this.sprites.frontFemale
    ?: this.sprites.frontShinyFemale,
    gifUrl = sprites.versions?.generationV?.blackWhite?.animated?.let {
        it.frontDefault ?: it.frontShiny ?: it.frontFemale ?: it.frontShinyFemale
    }
)

fun Pokemon.toPokemonEntity() = PokemonEntity(
    abilities,
    baseExperience,
    soundUrl,
    height,
    id,
    moves,
    name,
    species,
    imageUrl,
    gifUrl,
    stats,
    types,
    weight
)