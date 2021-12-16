package com.example.a160418034_projectuts.view

import android.view.View
import com.example.a160418034_projectuts.model.Recipe

interface RecipeDetailClick {
    fun onRecipeDetialClick(v: View)
}

interface ButtonAddClick {
    fun onButtonAddClick(v:View)
}

interface ButtonDeleteClick {
    fun onButtonDeleteClick(v:View, obj:Recipe)
}

interface ButtonEditClick {
    fun onButtonEditClick(v:View, obj:Recipe)
}

interface ButtonLogin{
    fun onButtonLoginClick(v:View)
}