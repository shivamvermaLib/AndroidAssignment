package com.shivam.androidassignment.state

import com.shivam.androidassignment.data.models.BottomSheetItem
import com.shivam.androidassignment.data.models.MainItem
import com.shivam.androidassignment.data.models.SubItem

data class HomeUiState(
    val items: List<MainItem> = emptyList(),
    val selectedMainItem: MainItem? = null,
    val subItemList: List<SubItem> = emptyList(),
    val bottomSheetItems: List<BottomSheetItem> = emptyList()
)