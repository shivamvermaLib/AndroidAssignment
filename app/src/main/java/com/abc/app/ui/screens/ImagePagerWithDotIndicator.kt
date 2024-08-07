package com.abc.app.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.abc.app.MainItem
import com.abc.app.ui.utils.DotIndicators


@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.pagerWithDotIndicator(items: List<MainItem>, onPageChanged: (Int) -> Unit) {
    item {
        val pagerState = rememberPagerState(pageCount = { items.size })
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect {
                onPageChanged(it)
            }
        }
        Column {
            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
                state = pagerState,
            ) {
                MainItem(items[it])
            }
            DotIndicators(
                pageCount = items.size,
                pagerState = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )
        }
    }
}

@Composable
fun MainItem(mainItem: MainItem) {
    AsyncImage(
        model = mainItem.imageUrl,
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}