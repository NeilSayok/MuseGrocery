package com.neilsayok.musewearables.data.model


import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import kotlinx.parcelize.Parcelize

class GetCategoryByTypeResponse : ArrayList<GetCategoryByTypeResponseItem>()

@Keep
@Parcelize
data class GetCategoryByTypeResponseItem(
    @SerializedName("description") val description: String?,
    @SerializedName("pricePerPiece") val pricePerPiece: Double?,
    @SerializedName("sliderImages") val sliderImages: List<String?>?,
    @SerializedName("thumbnailImage") val thumbnailImage: String?,
    @SerializedName("typeID") val typeID: Int?,
    @SerializedName("typeName") val typeName: String?,
    @SerializedName("weightPerPiece") val weightPerPiece: Int?
) : Parcelable{
    fun toCart(quantity : Int?) = Cart(
        typeID = typeID?:Int.MIN_VALUE,
        name = typeName?: EMPTY_STRING,
        pricePerPiece = pricePerPiece,
        quantity = quantity?:1
    )

    fun toLike(updatedLike: Boolean?): Like = Like(
        typeID = typeID?:Int.MIN_VALUE,
        name = typeName?: EMPTY_STRING,
        liked = updatedLike
    )
}
