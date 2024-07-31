package com.abc.app.ui.screens.states

import com.abc.app.data.models.BottomSheetItem
import com.abc.app.data.models.MainItem
import com.abc.app.data.models.SubItem

data class HomeUiState(
    val items: List<MainItem> = emptyList(),
    val selectedMainItem: MainItem? = null,
    val subItemList: List<SubItem> = emptyList(),
    val bottomSheetItems: List<BottomSheetItem> = emptyList()
)