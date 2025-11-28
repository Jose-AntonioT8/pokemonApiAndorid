package com.turingalan.pokemon.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase(): RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao
//    companion object {
//
//        @Volatile
//        private var INSTANCE: PokemonDatabase? = null
//
//        fun getDatabase(context: Context): PokemonDatabase {
//
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    PokemonDatabase::class.java,
//                    "pokemon_database"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
}