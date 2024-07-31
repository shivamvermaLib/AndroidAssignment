package com.shivam.androidassignment

data class MainItem(
    val imageUrl: String, val list: List<SubItem>
)

data class SubItem(
    val title: String, val desc: String, val imageUrl: String
)

data class BottomSheetItem(
    val count: Int,
    val characterCount: Map<String, Int>
)
