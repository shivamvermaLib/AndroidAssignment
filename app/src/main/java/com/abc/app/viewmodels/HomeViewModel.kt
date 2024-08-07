package com.abc.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abc.app.state.HomeUiState
import com.abc.app.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    itemRepository: ItemRepository
) : ViewModel() {

    private val _listFlow = MutableStateFlow(itemRepository.getItems())
    private val _selectedMainItemIndex = MutableStateFlow(0)
    private val _searchQueryFlow = MutableStateFlow("")
    private val _bottomSheetItemsFlow = MutableStateFlow(itemRepository.getBottomSheetItems())

    @OptIn(FlowPreview::class)
    val uiStateFlow =
        combine(
            _listFlow,
            _selectedMainItemIndex,
            _searchQueryFlow.debounce(300L),
            _bottomSheetItemsFlow
        ) { list, selectedItemIndex, search, bottomSheetList ->
            val item = list[selectedItemIndex]
            HomeUiState(
                list,
                item,
                item.list.filter {
                    search.isEmpty() || (search.isNotEmpty() && (it.title.lowercase()
                        .contains(search) || it.desc.lowercase().contains(search)))
                },
                bottomSheetList
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), HomeUiState())


    fun setSearch(search: String) {
        _searchQueryFlow.value = search.lowercase()
    }

    fun setPageIndex(itemIndex: Int) {
        _selectedMainItemIndex.value = itemIndex
    }

}