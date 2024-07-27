package com.neilsayok.musewearables.viewmodel

import com.neilsayok.musewearables.data.model.GetCategoriesResponse
import com.neilsayok.musewearables.data.model.GetCategoriesResponse.GetCategoriesResponseItem
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponse.GetCategoryByTypeResponseItem

sealed class MainEvent() {
    data object SetIdealEvent : MainEvent()
    data object CleanSelectedEvent : MainEvent()

    data object GetCategoriesEvent : MainEvent()
    data object GetCategoriesByTypeEvent : MainEvent()
    data class CategoryClick(val selectedCategory: GetCategoriesResponseItem) : MainEvent()
    data class PLPClick(val selectedItem: GetCategoryByTypeResponseItem) : MainEvent()

}