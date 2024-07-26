package com.neilsayok.musewearables.domain

import com.neilsayok.musewearables.data.constants.API_URL_GET_CATEGORIES
import com.neilsayok.musewearables.data.constants.API_URL_GET_CATEGORIES_BY_TYPE
import com.neilsayok.musewearables.data.constants.PATH_PARAM_CATEGORY_TYPE
import com.neilsayok.musewearables.data.model.GetCategoriesResponse
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponse
import com.neilsayok.musewearables.domain.services.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

class MainRepo @Inject constructor(val apiInterface: ApiInterface) {

    suspend fun getCategories(): GetCategoriesResponse = withContext(Dispatchers.IO){
        apiInterface.getCategories()
    }

    suspend fun getCategoriesByType(categoryType : String): GetCategoryByTypeResponse = withContext(Dispatchers.IO){
        apiInterface.getCategoriesByType(categoryType)
    }


}