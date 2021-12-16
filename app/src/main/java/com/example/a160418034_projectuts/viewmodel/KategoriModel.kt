package com.example.a160418034_projectuts.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.a160418034_projectuts.model.Kategori
import com.example.a160418034_projectuts.model.RecipeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class KategoriModel(application: Application): AndroidViewModel(application), CoroutineScope {
    val KategoriLD = MutableLiveData<List<Kategori>>()
    val KategoriLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        loadingLD.value = true
        KategoriLoadErrorLD.value = false
        launch {
            val db = Room.databaseBuilder(
                getApplication(),
                RecipeDatabase::class.java, "newrecipedb").build()

            KategoriLD.value = db.kategoriDao().selectAllKategori()
            loadingLD.value = false
        }
    }

    fun addTodo(list:List<Kategori>) {
        launch {
            val db = Room.databaseBuilder(
                getApplication(), RecipeDatabase::class.java,
                "newrecipedb").build()
            db.kategoriDao().insertAll(*list.toTypedArray())
        }
    }

    fun ClearKategori() {
        launch {
            val db = Room.databaseBuilder(
                    getApplication(), RecipeDatabase::class.java,
                    "newrecipedb").build()
            db.kategoriDao().nukeKategori()
        }
    }

//    fun refresh() {
//        val kategori1 = Kategori("Ayam")
//        val kategori2 = Kategori( "Nasi")
//
//        val KategoriList:ArrayList<Kategori> = arrayListOf<Kategori>(kategori1,kategori2)
//
//        KategoriLD.value = KategoriList
//        KategoriLoadErrorLD.value = false
//        loadingLD.value = false
//    }
}