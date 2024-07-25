package com.neilsayok.musewearables.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.neilsayok.musewearables.ui.common.AppLoader
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class Screen() {


    abstract fun setNavHost(): NavHostController

    abstract fun setLoadingState(): StateFlow<Boolean>

    @Composable
    abstract fun Content()

    @Composable
    abstract fun Toolbar()

    @Composable
    abstract fun StickyBottomSheet()


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BaseScreen() {

        val loading = setLoadingState().collectAsState()

        val bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.PartiallyExpanded)

        val scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = bottomSheetState
        )


        BottomSheetScaffold(
            sheetPeekHeight = 0.dp,
            sheetDragHandle = {},
            sheetSwipeEnabled = true,
            sheetContent = { StickyBottomSheet() },
            topBar = { Toolbar() },
            scaffoldState = scaffoldState
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Content()
                if (loading.value) {
                    AppLoader()
                }

            }
        }
    }


}