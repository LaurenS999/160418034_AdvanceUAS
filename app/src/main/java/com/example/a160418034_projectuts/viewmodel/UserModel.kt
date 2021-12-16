package com.example.a160418034_projectuts.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.a160418034_projectuts.model.*
import com.example.a160418034_projectuts.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UserModel(application: Application):AndroidViewModel(application),CoroutineScope {
    val UserLD = MutableLiveData<List<User>>()
    val UserDetail = MutableLiveData<User>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

//    fun fetch() {
//        val user1 = User( "Lauren","123","Basuki Rahmat","123456789", "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff")
//        UserLD.value = user1
//    }

    fun FetchUser(name:String, Password:String){
        launch {
            val db = Room.databaseBuilder(
                    getApplication(), RecipeDatabase::class.java,
                    "newrecipedb").build()
            UserDetail.value = db.userDao().SelectUser(name,Password)
        }
    }

    fun Fetch(){
        launch {
            val db = Room.databaseBuilder(
                    getApplication(), RecipeDatabase::class.java,
                    "newrecipedb").build()
            UserLD.value = db.userDao().SelectAllUser()
        }
    }

    fun adduser(list:List<User>) {
        launch {
            val db = Room.databaseBuilder(
                    getApplication(), RecipeDatabase::class.java,
                    "newrecipedb").build()
            db.userDao().insertAll(*list.toTypedArray())
        }
    }

    fun nukeUser(){
        launch{
            val db = buildDb(getApplication())
            db.userDao().nukeUser()
        }
    }
}