package com.neilsayok.musewearables.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

class GetCategoryByTypeResponse : ArrayList<GetCategoryByTypeResponse.GetCategoryByTypeResponseItem>(){
    @Keep
    @Parcelize
    data class GetCategoryByTypeResponseItem(
        @SerializedName("description")
        val description: String?,
        @SerializedName("pricePerPiece")
        val pricePerPiece: Double?,
        @SerializedName("sliderImages")
        val sliderImages: List<String?>?,
        @SerializedName("thumbnailImage")
        val thumbnailImage: String?,
        @SerializedName("typeID")
        val typeID: Int?,
        @SerializedName("typeName")
        val typeName: String?,
        @SerializedName("weightPerPiece")
        val weightPerPiece: Int?
    ) : Parcelable
}