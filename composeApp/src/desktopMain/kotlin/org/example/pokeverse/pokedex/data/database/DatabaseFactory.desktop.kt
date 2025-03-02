package org.example.pokeverse.pokedex.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<FavoritePokemonDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "PokeVerse")
            os.contains("mac") -> File(userHome, "Library/Application Support/PokeVerse")
            else -> File(userHome, ".local/share/PokeVerse")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, FavoritePokemonDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}