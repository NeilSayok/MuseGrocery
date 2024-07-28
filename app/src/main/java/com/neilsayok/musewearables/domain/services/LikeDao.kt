package com.neilsayok.musewearables.domain.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponseItem
import com.neilsayok.musewearables.data.model.Like

@Dao
interface LikeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(like: Like)

    @Update
    suspend fun updateLike(like: Like)

    @Query("SELECT * FROM likes")
    suspend fun getLikes(): List<Like>

    @Query("SELECT * FROM likes WHERE typeID = :id and name = :name")
    suspend fun getLike(id: Int, name: String): Like?

    suspend fun getLike(item: GetCategoryByTypeResponseItem?): Like?{
        return item?.let { getLike(it.typeID?:Int.MIN_VALUE, it.typeName?: EMPTY_STRING) }
    }

    @Query("DELETE FROM likes")
    suspend fun clearLikes()

    @Query("DELETE FROM likes WHERE typeID = :id and name = :name")
    suspend fun deleteLike(id: Int, name: String)

    suspend fun deleteLike(item: GetCategoryByTypeResponseItem?){
        item?.let { deleteLike(it.typeID?:Int.MIN_VALUE, it.typeName?: EMPTY_STRING) }
    }


}