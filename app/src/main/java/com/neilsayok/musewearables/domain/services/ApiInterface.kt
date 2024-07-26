package com.neilsayok.musewearables.domain.services

import com.neilsayok.musewearables.data.constants.API_URL_GET_CATEGORIES
import com.neilsayok.musewearables.data.constants.API_URL_GET_CATEGORIES_BY_TYPE
import com.neilsayok.musewearables.data.constants.PATH_PARAM_CATEGORY_TYPE
import com.neilsayok.musewearables.data.model.GetCategoriesResponse
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface{

    @GET(API_URL_GET_CATEGORIES)
    suspend fun getCategories() : GetCategoriesResponse

    @GET(API_URL_GET_CATEGORIES_BY_TYPE)
    suspend fun getCategoriesByType( @Path(PATH_PARAM_CATEGORY_TYPE) categoryType : String) : GetCategoryByTypeResponse



}