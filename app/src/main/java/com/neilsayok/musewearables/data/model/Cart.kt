package com.neilsayok.musewearables.data.model

import androidx.room.Entity

@Entity(tableName = "cart", primaryKeys = ["typeID", "name"])
data class Cart(
    val typeID: Int,
    val name : String,
    val pricePerPiece: Double?,
    val quantity : Int?

    )