package com.example.a160418034_projectuts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a160418034_projectuts.model.Recipe
import com.example.a160418034_projectuts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RecipeDetailModel(application: Application):AndroidViewModel(application),CoroutineScope {
    val RecipeDetialLD = MutableLiveData<Recipe>()

    fun fetch(index: Int){
        launch {
            val db = buildDb(getApplication())
            RecipeDetialLD.value = db.recipeDao().selectRecipe(index)
        }
    }

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun addRecipe(list:List<Recipe>) {
        launch {
            val db = buildDb(getApplication())
            db.recipeDao().insertAll(*list.toTypedArray())
        }
    }

    fun EditRepice(id:Int, name:String, kategori:String, deskripsi:String, recipe:String, step:String) {
        launch {
            val db = buildDb(getApplication())
            db.recipeDao().EditRecipe(id,name,kategori,deskripsi, recipe,step)
        }
    }

    fun DeleteRecipe(recipe: Recipe) {
        launch {
            val db = buildDb(getApplication())
            db.recipeDao().deleteRecipe(recipe)
        }
    }


}