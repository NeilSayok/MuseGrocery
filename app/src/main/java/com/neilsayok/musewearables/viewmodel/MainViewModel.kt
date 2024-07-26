package com.neilsayok.musewearables.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.data.error.ErrorEventData
import com.neilsayok.musewearables.data.error.Resource
import com.neilsayok.musewearables.data.error.toLoading
import com.neilsayok.musewearables.domain.MainRepo
import com.neilsayok.musewearables.domain.handler.coroutineExceptionHandlerWithFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepo: MainRepo,
    val errorLiveData: MutableStateFlow<Resource<ErrorEventData?>?>
) : ViewModel() {

    var mainUIState = mutableStateOf(MainUIState())


    fun onEvent(event: MainEvent){
        when(event){
            is MainEvent.GetCategoriesByTypeEvent -> {
                getCategoriesByType(mainUIState.value.selectedCategory)
            }
            MainEvent.GetCategoriesEvent -> {
                getCategories()
            }

            is MainEvent.CategoryClick -> {
                mainUIState.value = mainUIState.value.copy(
                    selectedCategory = event.categoryType
                )
            }
            MainEvent.CleanSelectedEvent -> {
                mainUIState.value = mainUIState.value.copy(
                    selectedCategory = EMPTY_STRING
                )
            }

            MainEvent.SetIdealEvent -> {
                mainUIState.value = mainUIState.value.copy(
                    isIdeal = true,
                    isLoading = false,

                    isGetCategoriesSuccess = false,
                    isGetCategoriesByTypeSuccess = false
                )
            }


        }
    }


    private fun getCategories() {

        val exceptionHandler = coroutineExceptionHandlerWithFlow(
            viewModelScope,
            mainUIState.value.getCategoriesResponse,
            errorData = errorLiveData,
            forceToast = true
        )

        viewModelScope.launch {
            mainUIState.value = mainUIState.value.copy(
                isIdeal = false, isLoading = true)
            mainUIState.value.getCategoriesResponse.apply {
                emit(value.toLoading())
            }
        }
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val apiResponseData = mainRepo.getCategories()

            mainUIState.value = mainUIState.value.copy(
                isIdeal = true,
                isLoading = false,
                isGetCategoriesSuccess = true,
                getCategories = apiResponseData.toMutableList()
            )
            mainUIState.value.getCategoriesResponse.emit(Resource.success(apiResponseData))
        }

    }

    private fun getCategoriesByType(categoryType : String) {

        val exceptionHandler = coroutineExceptionHandlerWithFlow(
            viewModelScope,
            mainUIState.value.getCategoriesByTypeResponse,
            errorData = errorLiveData,
            forceToast = true
        )

        viewModelScope.launch {
            mainUIState.value = mainUIState.value.copy(
                isIdeal = false, isLoading = true)
            mainUIState.value.getCategoriesByTypeResponse.apply {
                emit(value.toLoading())
            }
        }
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val apiResponseData = mainRepo.getCategoriesByType(categoryType)

            mainUIState.value = mainUIState.value.copy(
                isIdeal = true,
                isLoading = false,
                isGetCategoriesByTypeSuccess = true,
                getCategoriesByType = apiResponseData.toMutableList()
            )
            mainUIState.value.getCategoriesByTypeResponse.emit(Resource.success(apiResponseData))
        }

    }


}