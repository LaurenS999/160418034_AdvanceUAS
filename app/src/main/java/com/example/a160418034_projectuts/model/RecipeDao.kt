package com.example.a160418034_projectuts.model

import androidx.room.*

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg recipe:Recipe)

    @Query("SELECT * FROM Recipe")
    suspend fun selectAllRecipe(): List<Recipe>

    @Query("SELECT * FROM Recipe where kategori= :kategori")
    suspend fun selectRecipeByKategori(kategori:String): List<Recipe>

    @Query("SELECT * FROM Recipe where favorite = 1")
    suspend fun selectRecipeByFavorite(): List<Recipe>

    @Query("SELECT * FROM Recipe WHERE id= :id")
    suspend fun selectRecipe(id:Int): Recipe

    @Query("UPDATE Recipe SET name= :name, kategori= :kategori, description= :deskripsi, recipe= :recipe, step= :step WHERE id = :id")
    suspend fun EditRecipe(id:Int, name:String, kategori:String, deskripsi:String, recipe:String, step:String)

    @Query("DELETE FROM Recipe")
    suspend fun nukeRecipe()

    @Delete
    suspend fun deleteRecipe(recipe:Recipe)
}

