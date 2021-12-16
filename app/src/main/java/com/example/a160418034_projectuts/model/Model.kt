package com.example.a160418034_projectuts.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @ColumnInfo(name="name")
    val name:String?,
    @ColumnInfo(name="kategori")
    val Kategori:String?,
    @ColumnInfo(name="recipe")
    val Recipe:String?,
    @ColumnInfo(name="description")
    val description:String?,
    @ColumnInfo(name="step")
    val step:String?,
    @ColumnInfo(name="photoUrl")
    val photoUrl:String?,
    @ColumnInfo(name="favorite")
    val favorite:Int?
){
    @PrimaryKey(autoGenerate = true)
    var id:Int =0

}

@Entity
data class User(
    @ColumnInfo(name="name")
    val Username:String?,
    @ColumnInfo(name="password")
    val password:String?,
    @ColumnInfo(name="address")
    val address:String?,
    @ColumnInfo(name="phone")
    val phone:String?,
    @ColumnInfo(name="photoUrl")
    val photoUrl:String?
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int =0

}

@Entity
data class Kategori(
    @ColumnInfo(name="name")
    val name:String?,
){
    @PrimaryKey(autoGenerate = true)
    var id:Int =0

}
