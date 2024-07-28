package com.neilsayok.musewearables.viewmodel

import com.neilsayok.musewearables.data.model.GetCategoriesResponse.GetCategoriesResponseItem
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponseItem

sealed class MainEvent() {
    data object SetIdealEvent : MainEvent()

    //Value Cleaning Events
    data object CleanSelectedEvent : MainEvent()
    data object CleanPDPEvent : MainEvent()

    //API Call Events
    data object GetCategoriesEvent : MainEvent()
    data object GetCategoriesByTypeEvent : MainEvent()

    //UI Events
    data class CategoryClick(val selectedCategory: GetCategoriesResponseItem) : MainEvent()
    data class PLPClick(val selectedItem: GetCategoryByTypeResponseItem) : MainEvent()

    data class GetCartCountForItem(val selectedItem: GetCategoryByTypeResponseItem) : MainEvent()
    data object GetTotalCartCount: MainEvent()
    data class IncreaseItemInCart(val selectedItem: GetCategoryByTypeResponseItem) : MainEvent()
    data class DecreaseItemInCart(val selectedItem: GetCategoryByTypeResponseItem) : MainEvent()


    data class PressLike(val selectedItem: GetCategoryByTypeResponseItem) : MainEvent()
    data class IsItemLikedLike(val selectedItem: GetCategoryByTypeResponseItem) : MainEvent()
    data class SaveCard(val cardNumber: String) : MainEvent()
    data class SearchCategories(val searchTerm: String) : MainEvent()
    data class SearchPLP(val searchTerm: String) : MainEvent()

    data object ClearCart : MainEvent()



}