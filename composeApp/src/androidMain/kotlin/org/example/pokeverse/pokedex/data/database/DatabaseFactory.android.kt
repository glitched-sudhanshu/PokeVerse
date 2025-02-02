package org.example.pokeverse.pokedex.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<FavoritePokemonDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(FavoritePokemonDatabase.DB_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}