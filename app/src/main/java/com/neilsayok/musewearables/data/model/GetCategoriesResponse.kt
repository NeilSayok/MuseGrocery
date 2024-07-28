package com.neilsayok.musewearables.data.model


import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class GetCategoriesResponse : ArrayList<GetCategoriesResponse.GetCategoriesResponseItem>() {
    @Keep
    @Parcelize
    data class GetCategoriesResponseItem(
        @SerializedName("categoryID") val categoryID: Int?,
        @SerializedName("categoryImage") val categoryImage: String?,
        @SerializedName("categoryName") val categoryName: String?,
        @SerializedName("categoryType") val categoryType: String?,
        @SerializedName("totalItems") val totalItems: Int?
    ) : Parcelable
}