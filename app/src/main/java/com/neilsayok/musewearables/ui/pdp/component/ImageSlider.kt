package com.neilsayok.musewearables.ui.pdp.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.neilsayok.musewearables.utils.loadImage


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(modifier: Modifier = Modifier, imageUrlList: List<String>?, pagerState: PagerState) {



    Box(modifier = Modifier.then(modifier)) {

        if (!imageUrlList.isNullOrEmpty()) {
            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) {

                imageUrlList.forEach { image ->
                    Image(painter = loadImage(url = image),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,modifier = Modifier.fillMaxSize())
                }

            }


        }
    }
}

