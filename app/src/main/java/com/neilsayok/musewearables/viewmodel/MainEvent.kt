package com.neilsayok.musewearables.viewmodel

sealed class MainEvent() {
    data object SetIdealEvent : MainEvent()
    data object CleanSelectedEvent : MainEvent()

    data object GetCategoriesEvent : MainEvent()
    data class GetCategoriesByTypeEvent(val categoryType : String) : MainEvent()
    data class CategoryClick(val categoryType : String) : MainEvent()

}