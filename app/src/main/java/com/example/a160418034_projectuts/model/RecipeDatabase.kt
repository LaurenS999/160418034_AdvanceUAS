package com.example.a160418034_projectuts.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = (arrayOf(Recipe::class, Kategori::class, User::class)), version =  3)
abstract class RecipeDatabase:RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun kategoriDao(): KategoriDao
    abstract fun userDao(): UserDao

    companion object{
        @Volatile private var instance: RecipeDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RecipeDatabase::class.java,
                "newrecipedb")
                    .build()

        operator fun invoke(context:Context) {
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}