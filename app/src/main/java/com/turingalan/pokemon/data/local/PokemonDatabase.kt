package com.turingalan.pokemon.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PokemonEntity::class], version = 2)
abstract class PokemonDatabase(): RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao

}