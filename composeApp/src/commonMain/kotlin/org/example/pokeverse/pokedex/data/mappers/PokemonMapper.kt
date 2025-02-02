package org.example.pokeverse.pokedex.data.mappers

import org.example.pokeverse.pokedex.data.dto.AbilityDto
import org.example.pokeverse.pokedex.data.dto.AnimatedDto
import org.example.pokeverse.pokedex.data.dto.BlackWhiteDto
import org.example.pokeverse.pokedex.data.dto.CriesDto
import org.example.pokeverse.pokedex.data.dto.GenerationVDto
import org.example.pokeverse.pokedex.data.dto.GenericNamedResourceDto
import org.example.pokeverse.pokedex.data.dto.MoveDto
import org.example.pokeverse.pokedex.data.dto.PokemonDto
import org.example.pokeverse.pokedex.data.dto.SpritesDto
import org.example.pokeverse.pokedex.data.dto.StatDto
import org.example.pokeverse.pokedex.data.dto.TypeDto
import org.example.pokeverse.pokedex.data.dto.VersionsDto
import org.example.pokeverse.pokedex.domain.model.Ability
import org.example.pokeverse.pokedex.domain.model.Animated
import org.example.pokeverse.pokedex.domain.model.BlackWhite
import org.example.pokeverse.pokedex.domain.model.Cries
import org.example.pokeverse.pokedex.domain.model.GenerationV
import org.example.pokeverse.pokedex.domain.model.GenericNamedResource
import org.example.pokeverse.pokedex.domain.model.Move
import org.example.pokeverse.pokedex.domain.model.Pokemon
import org.example.pokeverse.pokedex.domain.model.Sprites
import org.example.pokeverse.pokedex.domain.model.Stat
import org.example.pokeverse.pokedex.domain.model.Type
import org.example.pokeverse.pokedex.domain.model.Versions

fun GenericNamedResourceDto.toGenericNamedResource(): GenericNamedResource {
    return GenericNamedResource(name = this.name, url = this.url)
}

fun AbilityDto.toAbility(): Ability {
    return Ability(ability = this.ability.toGenericNamedResource(), slot = this.slot)
}

fun AnimatedDto.toAnimated() = Animated(
    backDefault,
    backFemale,
    backShiny,
    backShinyFemale,
    frontDefault,
    frontFemale,
    frontShiny,
    frontShinyFemale
)

fun BlackWhiteDto.toBlackWhite() = BlackWhite(animated = this.animated.toAnimated())

fun CriesDto.toCries() = Cries(latest, legacy)

fun GenerationVDto.toGenerationV() = GenerationV(blackWhite = this.blackWhite.toBlackWhite())

fun MoveDto.toMove() = Move(move = this.move.toGenericNamedResource())

fun SpritesDto.toSprites() =
    Sprites(
        backDefault,
        backFemale,
        backShiny,
        backShinyFemale,
        frontDefault,
        frontFemale,
        frontShiny,
        frontShinyFemale,
        versions?.toVersions()
    )

fun VersionsDto.toVersions() = Versions(generationV = this.generationV.toGenerationV())

fun StatDto.toStat() =
    Stat(stat = this.stat.toGenericNamedResource(), baseStat = this.baseStat, effort = this.effort)

fun TypeDto.toType() = Type(type = this.type.toGenericNamedResource(), slot = this.slot)

fun PokemonDto.toPokemon() = Pokemon(
    id = this.id,
    name = this.name,
    cries = this.cries.toCries(),
    species = this.species.toGenericNamedResource(),
    height = this.height,
    weight = this.weight,
    moves = this.moves.map { it.toMove() },
    stats = this.stats.map { it.toStat() },
    baseExperience = this.baseExperience,
    types = this.types.map { it.toType() },
    sprites = this.sprites.toSprites(),
    abilities = this.abilities.map { it.toAbility() }
)