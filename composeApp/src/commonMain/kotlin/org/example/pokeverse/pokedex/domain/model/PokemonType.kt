package org.example.pokeverse.pokedex.domain.model

sealed class PokemonType(val name: String) {
    data object Normal : PokemonType("normal")
    data object Fighting : PokemonType("fighting")
    data object Flying : PokemonType("flying")
    data object Poison : PokemonType("poison")
    data object Ground : PokemonType("ground")
    data object Rock : PokemonType("rock")
    data object Bug : PokemonType("bug")
    data object Ghost : PokemonType("ghost")
    data object Steel : PokemonType("steel")
    data object Fire : PokemonType("fire")
    data object Water : PokemonType("water")
    data object Grass : PokemonType("grass")
    data object Electric : PokemonType("electric")
    data object Psychic : PokemonType("psychic")
    data object Ice : PokemonType("ice")
    data object Dragon : PokemonType("dragon")
    data object Dark : PokemonType("dark")
    data object Fairy : PokemonType("fairy")
    data object Stellar : PokemonType("stellar")
    data object Unknown : PokemonType("unknown")

    companion object {
        fun fromName(name: String): PokemonType {
            return when (name.lowercase()) {
                "normal" -> Normal
                "fighting" -> Fighting
                "flying" -> Flying
                "poison" -> Poison
                "ground" -> Ground
                "rock" -> Rock
                "bug" -> Bug
                "ghost" -> Ghost
                "steel" -> Steel
                "fire" -> Fire
                "water" -> Water
                "grass" -> Grass
                "electric" -> Electric
                "psychic" -> Psychic
                "ice" -> Ice
                "dragon" -> Dragon
                "dark" -> Dark
                "fairy" -> Fairy
                "stellar" -> Stellar
                "unknown" -> Unknown
                else -> Unknown
            }
        }
    }
}
