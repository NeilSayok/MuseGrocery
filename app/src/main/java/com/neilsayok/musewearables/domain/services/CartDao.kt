package com.neilsayok.musewearables.domain.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.data.model.Cart
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponseItem

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cart: Cart)

    @Update
    suspend fun updateCartLine(cart: Cart)

    @Query("SELECT * FROM cart")
    suspend fun getCartItems(): List<Cart>

    @Query("SELECT quantity FROM cart")
    suspend fun getCartCount(): List<Int?>

    @Query("SELECT * FROM cart WHERE typeID = :id and name = :name")
    suspend fun getCartItem(id: Int,name: String): Cart?

    suspend fun getCartItem(item: GetCategoryByTypeResponseItem?): Cart?{
        return item?.let { getCartItem(it.typeID?:Int.MIN_VALUE,it.typeName?: EMPTY_STRING) }
    }

    @Query("DELETE FROM cart")
    suspend fun clearCart()

    @Query("DELETE FROM cart WHERE typeID = :id and name = :name")
    suspend fun deleteCartItem(id: Int, name: String)

    @Query("DELETE FROM cart")
    suspend fun deleteCart()

    suspend fun deleteCartItem(item: GetCategoryByTypeResponseItem?){
        item?.let { deleteCartItem(it.typeID?: Int.MIN_VALUE,it.typeName?: EMPTY_STRING) }
    }







}