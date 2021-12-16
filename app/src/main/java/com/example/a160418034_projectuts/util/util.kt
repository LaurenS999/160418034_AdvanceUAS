package com.example.a160418034_projectuts.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a160418034_projectuts.R
import com.example.a160418034_projectuts.model.RecipeDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

val DB_NAME = "newrecipedb"

fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
            .load(url)
            .resize(400, 400)
            .centerCrop()
            .error(R.drawable.ic_baseline_error_24)
            .into(this, object: Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.GONE
                }
                override fun onError(e: Exception?) {
                }
            })
}

@BindingAdapter("android:imageUrl", "android:progressBar")
fun loadPhotoUrl(view:ImageView, url:String?, pb:ProgressBar){
    view.loadImage(url,pb)
}

fun buildDb(context: Context):RecipeDatabase {
    val db = Room.databaseBuilder(context,
        RecipeDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()

    return db
}

val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `Kategori` (`id` INTEGER NOT NULL, `name` TEXT, PRIMARY KEY(`id`))")
    }
}

val MIGRATION_2_3: Migration = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `User` (`id` INTEGER NOT NULL, `name` TEXT, `password` TEXT, `address` TEXT, `phone` TEXT, `photoUrl` TEXT , PRIMARY KEY(`id`))")
    }
}

fun createNotificationChannel(context: Context, importance: Int, showBadge:
Boolean, name: String, description: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        channel.setShowBadge(showBadge)
        val notificationManager =
            context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}