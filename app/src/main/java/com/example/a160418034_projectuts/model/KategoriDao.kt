package com.example.a160418034_projectuts.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KategoriDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg kategori:Kategori)

    @Query("SELECT * FROM Kategori")
    suspend fun selectAllKategori(): List<Kategori>

    @Query("DELETE FROM Kategori")
    suspend fun nukeKategori()
}