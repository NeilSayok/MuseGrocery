package com.neilsayok.musewearables.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.data.error.ErrorEventData
import com.neilsayok.musewearables.data.error.Resource
import com.neilsayok.musewearables.data.error.toLoading
import com.neilsayok.musewearables.data.model.GetCategoryByTypeResponseItem
import com.neilsayok.musewearables.domain.handler.coroutineExceptionHandlerWithFlow
import com.neilsayok.musewearables.domain.repo.MainRepo
import com.neilsayok.musewearables.domain.services.CartDao
import com.neilsayok.musewearables.domain.services.LikeDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepo: MainRepo,
    private val cartDao: CartDao,
    private val likeDao: LikeDao,
    private val errorLiveData: MutableStateFlow<Resource<ErrorEventData?>?>
) : ViewModel() {

    var mainUIState = mutableStateOf(MainUIState())


    fun onEvent(event: MainEvent){
        when(event){
            is MainEvent.GetCategoriesByTypeEvent -> {
                mainUIState.value.selectedCategory?.categoryType?.let { getCategoriesByType(it) }
            }
            MainEvent.GetCategoriesEvent -> {
                getCategories()
            }



            is MainEvent.CategoryClick -> {
                mainUIState.value = mainUIState.value.copy(
                    selectedCategory = event.selectedCategory
                )
            }

            is MainEvent.PLPClick -> {
                mainUIState.value = mainUIState.value.copy(
                    selectedPLPItem = event.selectedItem
                )
            }


            MainEvent.CleanSelectedEvent -> {
                mainUIState.value = mainUIState.value.copy(
                    selectedCategory = null
                )
            }

            is MainEvent.GetCartCountForItem -> getCartCountForItem(event.selectedItem)
            is MainEvent.DecreaseItemInCart -> decreaseItemCart(event.selectedItem)
            is MainEvent.IncreaseItemInCart -> increaseItemCart(event.selectedItem)
            MainEvent.GetTotalCartCount -> getTotalCartCount()

            is MainEvent.IsItemLikedLike -> getItemLike(event.selectedItem)
            is MainEvent.PressLike -> likeItem( event.selectedItem)

            is MainEvent.CleanPDPEvent ->{
                mainUIState.value = mainUIState.value.copy(
                    cartCount = 0,
                    isLiked = false
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

    private fun getCartCountForItem(item: GetCategoryByTypeResponseItem?){
        viewModelScope.launch {
            val cartItem = item?.let { cartDao.getCartItem(it) }

            mainUIState.value = mainUIState.value.copy(
                cartCount = cartItem?.quantity?:0
            )
        }
    }

    private fun increaseItemCart(item: GetCategoryByTypeResponseItem?) {
        viewModelScope.launch {
            val cartItem = item?.let { cartDao.getCartItem(it) }

            if (cartItem == null) {
                item?.let { cartDao.insert(it.toCart(1)) }
            } else {
                val updatedQuantity = cartItem.quantity?.plus(1) ?: 1
                item.let { cartDao.updateCartLine(it.toCart(updatedQuantity)) }
            }
            getCartCountForItem(item)
        }
    }

    private fun decreaseItemCart(item: GetCategoryByTypeResponseItem?) {
        viewModelScope.launch {
            val cartItem = item?.let { cartDao.getCartItem(it) }

            if (cartItem != null) {

                val updatedQuantity = cartItem.quantity?.minus(1)

                if (updatedQuantity == 0) {
                    cartDao.deleteCartItem(item)
                } else {
                    cartDao.updateCartLine(item.toCart(updatedQuantity))
                }
            }
            getCartCountForItem(item)

        }
    }

    private fun getTotalCartCount(){
        viewModelScope.launch {
            val cartCount = cartDao.getCartCount().filterNotNull().sum()

            mainUIState.value = mainUIState.value.copy(
                totalCartCount = if (cartCount < 10) {
                    if (cartCount > 1) {
                        "$cartCount"
                    } else {
                        EMPTY_STRING
                    }
                } else {
                    "9+"
                }
            )
        }
    }


    private fun getItemLike(item: GetCategoryByTypeResponseItem?){
        viewModelScope.launch {
            val likeItem = item?.let { likeDao.getLike(it) }

            mainUIState.value = mainUIState.value.copy(
                isLiked = likeItem?.liked?:false
            )
        }
    }

    private fun likeItem(item: GetCategoryByTypeResponseItem?) {
        viewModelScope.launch {
            val likeItem = item?.let { likeDao.getLike(it) }

            if (likeItem == null){
                item?.let { likeDao.insert(it.toLike(true)) }
            }else{
                val updatedLike = likeItem.liked?.not()
                if (updatedLike == true)
                    item.let { likeDao.updateLike(it.toLike(updatedLike)) }
                else
                    item.let { likeDao.deleteLike(item) }

            }

            getItemLike(item)

        }
    }




}