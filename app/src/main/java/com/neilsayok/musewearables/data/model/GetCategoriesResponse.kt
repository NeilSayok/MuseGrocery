package com.neilsayok.musewearables.data.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

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