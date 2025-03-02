import androidx.compose.ui.graphics.Color
import org.r02_sudhanshu.pokeverse.pokedex.domain.model.PokemonType

fun getPokemonTypeColor(type: PokemonType?): Color? {
    val pokemonTypeColors = mapOf(
        PokemonType.Normal to Color(0xFFA8A77A),
        PokemonType.Fighting to Color(0xFFC22E28),
        PokemonType.Flying to Color(0xFFA98FF3),
        PokemonType.Poison to Color(0xFFA33EA1),
        PokemonType.Ground to Color(0xFFE2BF65),
        PokemonType.Rock to Color(0xFFB6A136),
        PokemonType.Bug to Color(0xFFA6B91A),
        PokemonType.Ghost to Color(0xFF735797),
        PokemonType.Steel to Color(0xFFB7B7CE),
        PokemonType.Fire to Color(0xFFEE8130),
        PokemonType.Water to Color(0xFF6390F0),
        PokemonType.Grass to Color(0xFF7AC74C),
        PokemonType.Electric to Color(0xFFF7D02C),
        PokemonType.Psychic to Color(0xFFF95587),
        PokemonType.Ice to Color(0xFF96D9D6),
        PokemonType.Dragon to Color(0xFF6F35FC),
        PokemonType.Dark to Color(0xFF705746),
        PokemonType.Fairy to Color(0xFFD685AD),
        PokemonType.Stellar to Color(0xFFFFD700),
        PokemonType.Unknown to Color(0xFF68A090)
    )
    return pokemonTypeColors[type]
}