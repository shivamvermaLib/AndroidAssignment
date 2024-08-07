package com.abc.app

data class HomeUiState(
    val items: List<MainItem> = emptyList(),
    val selectedMainItem: MainItem? = null,
    val subItemList: List<SubItem> = emptyList(),
    val bottomSheetItems: List<BottomSheetItem> = emptyList()
)