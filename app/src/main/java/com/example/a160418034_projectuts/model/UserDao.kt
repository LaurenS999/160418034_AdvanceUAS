package com.example.a160418034_projectuts.model

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg user:User)

    @Query("SELECT * FROM User")
    suspend fun SelectAllUser(): List<User>

    @Query("SELECT * FROM User where name=:name and password=:password")
    suspend fun SelectUser(name:String, password:String ): User

    @Delete
    suspend fun deleteUser(user:User)
}

