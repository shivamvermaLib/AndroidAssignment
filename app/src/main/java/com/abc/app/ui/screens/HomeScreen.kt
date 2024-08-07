package com.abc.app.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.abc.app.HomeViewModel

@Preview
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val uiState by viewModel.uiStateFlow.collectAsState()
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheet(uiState.bottomSheetItems) {
            showSheet = false
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet = true }) {
                Icon(imageVector = Icons.Rounded.Home, contentDescription = "")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            pagerWithDotIndicator(uiState.items, viewModel::setPageIndex)
            stickerHeaderSearchBar(viewModel::setSearch)
            items(uiState.subItemList) {
                ListItem(subItem = it)
            }
        }
    }
}

