package com.neilsayok.musewearables.ui.plp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.neilsayok.musewearables.ui.plp.components.PlpItem
import com.neilsayok.musewearables.utils.FontRoboto
import com.neilsayok.musewearables.utils.fontDimensionResource
import com.neilsayok.musewearables.utils.showToast
import com.neilsayok.musewearables.viewmodel.MainEvent
import com.neilsayok.musewearables.viewmodel.MainUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

class PlpScreen(
    private val navController: NavHostController,
    private val uiState: MainUIState,
    private val onEvent: (MainEvent) -> Unit,
) : Screen() {
    private val loadingState = MutableStateFlow(false)

    override fun setNavHost(): NavHostController = navController

    override fun setLoadingState(): StateFlow<Boolean> = loadingState

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {

        val context = LocalContext.current

        var searchterm by remember { mutableStateOf(EMPTY_STRING) }

        LaunchedEffect(key1 = searchterm) {
            delay(300)//Debouncing
            onEvent(MainEvent.SearchPLP(searchterm))
        }

        if (uiState.isGetCategoriesByTypeSuccess == true) {
            onEvent(MainEvent.SetIdealEvent)
        }


        //Error Case
        LaunchedEffect(uiState) {
            uiState.getCategoriesByTypeResponse.collectLatest {
                if (it?.status == Resource.Status.ERROR) {
                    onEvent(MainEvent.SetIdealEvent)
                    showToast(context, it.message)
                }
            }
        }

        LaunchedEffect(Unit) {
            onEvent(MainEvent.GetCategoriesByTypeEvent)
            onEvent(MainEvent.CleanPDPEvent)
        }








        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(
                    horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)
                )
        ) {
            Text(
                text = uiState.selectedCategory?.categoryName?: EMPTY_STRING,
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

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)),
                modifier = Modifier.weight(1f)
            ) {
                uiState.getCategoriesByType?.let {getCategoriesByType->
                items(getCategoriesByType) { catType->
                        PlpItem(catType){selectedCat->
                            onEvent(MainEvent.PLPClick(selectedCat))
                            navController.navigate(Routes.PDPScreen.path)
                        }
                    }

                }
            }


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