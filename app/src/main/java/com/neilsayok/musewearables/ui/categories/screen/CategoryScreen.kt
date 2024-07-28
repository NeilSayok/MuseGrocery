package com.neilsayok.musewearables.ui.categories.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.base.Screen
import com.neilsayok.musewearables.data.constants.EMPTY_STRING
import com.neilsayok.musewearables.data.error.Resource
import com.neilsayok.musewearables.navigation.route.Routes
import com.neilsayok.musewearables.theme.BackgroundColor
import com.neilsayok.musewearables.theme.BorderColor
import com.neilsayok.musewearables.ui.categories.components.CategoriesItem
import com.neilsayok.musewearables.ui.common.BottomNav
import com.neilsayok.musewearables.utils.FontRoboto
import com.neilsayok.musewearables.utils.fontDimensionResource
import com.neilsayok.musewearables.utils.showToast
import com.neilsayok.musewearables.viewmodel.MainEvent
import com.neilsayok.musewearables.viewmodel.MainUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

class CategoryScreen(private val navController: NavHostController,
                     private val uiState: MainUIState,
                     private val onEvent: (MainEvent)->Unit,) : Screen() {
    private val loadingState = MutableStateFlow(uiState.isLoading == true)

    override fun setNavHost(): NavHostController = navController

    override fun setLoadingState(): StateFlow<Boolean> = loadingState

    @Composable
    override fun Content() {

        val context = LocalContext.current

        var searchterm by remember { mutableStateOf(EMPTY_STRING) }

        val cartCount by remember { mutableIntStateOf(uiState.totalCartCount) }

        LaunchedEffect(key1 = Unit) {
            onEvent(MainEvent.GetTotalCartCount)
        }

        LaunchedEffect(key1 = searchterm) {
            delay(300)//Debouncing
            onEvent(MainEvent.SearchCategories(searchterm))
        }

        if (uiState.isGetCategoriesSuccess == true) {
            onEvent(MainEvent.SetIdealEvent)
        }


        //Error Case
        LaunchedEffect(uiState) {
            uiState.getCategoriesResponse.collectLatest {
                if (it?.status == Resource.Status.ERROR) {
                    onEvent(MainEvent.SetIdealEvent)
                    showToast(context, it.message)
                }
            }
        }

        LaunchedEffect(Unit) {
            onEvent(MainEvent.CleanSelectedEvent)
            onEvent(MainEvent.GetCategoriesEvent)
        }


        Column( modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(BackgroundColor)
                    .padding(
                        horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)
                    )
            ) {
                Text(
                    text = "Categories",
                    fontFamily = FontRoboto,
                    fontWeight = FontWeight.Medium,
                    fontSize = fontDimensionResource(
                        id = com.intuit.ssp.R.dimen._24ssp
                    )
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)))

                OutlinedTextField(value = searchterm,
                    onValueChange = {searchterm = it},
                    leadingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
                    placeholder = { Text(text = "Search") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = BorderColor,
                        unfocusedBorderColor = BorderColor
                    ),
                    shape = RoundedCornerShape(100),
                    modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)))

                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._16sdp)),
                    modifier = Modifier.weight(1f)
                ) {
                    uiState.getCategories?.let { getCat ->
                        items(getCat) { category ->
                            CategoriesItem(category) { selectedCategory ->
                                onEvent(MainEvent.CategoryClick(selectedCategory))
                                navController.navigate(Routes.PLPScreen.path)

                            }
                        }
                    }

                }




            }

            BottomNav(navController = navController, backgroundColor = BackgroundColor, cartCount= cartCount)

        }


    }


    @Composable
    override fun Toolbar() {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(dimensionResource(id = com.intuit.sdp.R.dimen._56sdp))
                .background(BackgroundColor)
        ) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                    contentDescription = "Navigate Up"
                )
            }
        }

    }
}