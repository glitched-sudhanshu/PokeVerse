package org.r02_sudhanshu.pokeverse.pokedex.data.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.r02_sudhanshu.pokeverse.pokedex.data.database.entities.AbilityEntity
import org.r02_sudhanshu.pokeverse.pokedex.data.database.entities.MoveEntity
import org.r02_sudhanshu.pokeverse.pokedex.data.database.entities.StatEntity
import org.r02_sudhanshu.pokeverse.pokedex.data.database.entities.TypeEntity

object PokemonTypeConverters {

    @TypeConverter
    fun fromAbilitiesList(value: List<AbilityEntity>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toAbilitiesList(value: String): List<AbilityEntity> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromMovesList(value: List<MoveEntity>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toMovesList(value: String): List<MoveEntity> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromStatsList(value: List<StatEntity>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStatsList(value: String): List<StatEntity> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromTypesList(value: List<TypeEntity>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toTypesList(value: String): List<TypeEntity> {
        return Json.decodeFromString(value)
    }
}
