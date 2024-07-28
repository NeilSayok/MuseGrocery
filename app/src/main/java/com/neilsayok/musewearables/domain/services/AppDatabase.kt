package com.neilsayok.musewearables.domain.services

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neilsayok.musewearables.data.model.Cart
import com.neilsayok.musewearables.data.model.Like

@Database(entities = [Cart::class, Like::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun likeDao(): LikeDao
}