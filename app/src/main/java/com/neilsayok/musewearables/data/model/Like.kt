package com.neilsayok.musewearables.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "likes", primaryKeys = ["typeID", "name"])
data class Like(
    val typeID: Int,
    val name : String,
    val liked: Boolean?

)