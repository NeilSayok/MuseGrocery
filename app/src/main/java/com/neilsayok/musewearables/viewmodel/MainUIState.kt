package com.neilsayok.musewearables.viewmodel

import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.data.error.Resource
import com.neilsayok.musewearables.data.model.GetCategoriesResponse
import com.neilsayok.musewearables.data.model.GetCategoriesResponse.*
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponse
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponse.*
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponseItem
import kotlinx.coroutines.flow.MutableStateFlow

data class MainUIState(
    val isLoading: Boolean? = false,
    val isError: Boolean? = false,
    val isIdeal: Boolean? = true,


    //API Success
    val isGetCategoriesSuccess : Boolean? = false,
    val isGetCategoriesByTypeSuccess : Boolean? = false,

    //API Response
    val getCategories: MutableList<GetCategoriesResponseItem>? = mutableListOf(),
    val getCategoriesResponse: MutableStateFlow<Resource<GetCategoriesResponse>?> = MutableStateFlow(Resource()),

    val getCategoriesByType: MutableList<GetCategoryByTypeResponseItem>? = mutableListOf(),
    val getCategoriesByTypeResponse: MutableStateFlow<Resource<GetCategoryByTypeResponse>?> = MutableStateFlow(Resource()),

    //Flow Data
    val selectedCategory : GetCategoriesResponseItem? = null,
    val selectedPLPItem : GetCategoryByTypeResponseItem? = null,

    val totalCartCount : String = EMPTY_STRING,
    val cartCount : Int = 0,
    val isLiked : Boolean = false,

    )