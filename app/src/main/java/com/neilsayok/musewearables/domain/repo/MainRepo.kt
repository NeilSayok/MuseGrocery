package com.neilsayok.musewearables.domain.repo

import com.neilsayok.musewearables.data.model.GetCategoriesResponse
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponse
import com.neilsayok.musewearables.domain.services.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepo @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getCategories(): GetCategoriesResponse = withContext(Dispatchers.IO){
        apiInterface.getCategories()
    }

    suspend fun getCategoriesByType(categoryType : String): GetCategoryByTypeResponse = withContext(Dispatchers.IO){
        apiInterface.getCategoriesByType(categoryType)
    }


}