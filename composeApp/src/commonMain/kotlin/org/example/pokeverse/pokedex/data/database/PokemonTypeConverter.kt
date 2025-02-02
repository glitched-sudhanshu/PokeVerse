package org.example.pokeverse.pokedex.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.pokeverse.pokedex.domain.model.Ability
import org.example.pokeverse.pokedex.domain.model.Move
import org.example.pokeverse.pokedex.domain.model.Stat
import org.example.pokeverse.pokedex.domain.model.Type

object PokemonTypeConverters {

    @TypeConverter
    fun fromAbilitiesList(value: List<Ability>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toAbilitiesList(value: String): List<Ability> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromMovesList(value: List<Move>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toMovesList(value: String): List<Move> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromStatsList(value: List<Stat>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStatsList(value: String): List<Stat> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromTypesList(value: List<Type>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toTypesList(value: String): List<Type> {
        return Json.decodeFromString(value)
    }
}
