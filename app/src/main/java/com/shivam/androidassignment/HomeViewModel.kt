package com.shivam.androidassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val items: List<MainItem> = listOf(
        MainItem(
            imageUrl = "https://images.pexels.com/photos/2662116/pexels-photo-2662116.jpeg?cs=srgb&dl=pexels-jaime-reimer-1376930-2662116.jpg&fm=jpg",
            list = listOf(
                SubItem(
                    title = "Beautiful Scenery 1",
                    desc = "A breathtaking view of nature.",
                    imageUrl = "https://img.freepik.com/free-photo/beautiful-natural-view-landscape_23-2150787996.jpg"
                ), SubItem(
                    title = "Serene Landscape",
                    desc = "A calm and peaceful landscape view.",
                    imageUrl = "https://images.unsplash.com/photo-1719297523639-b104d382c497?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ), SubItem(
                    title = "Mountain Path",
                    desc = "A beautiful path leading through the mountains.",
                    imageUrl = "https://thumbs.dreamstime.com/b/beautiful-view-nature-mountains-near-konigssee-lake-bavaria-germany-blue-sky-clouds-97444419.jpg"
                ), SubItem(
                    title = "Sunrise Glory",
                    desc = "A stunning sunrise over a serene lake.",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIA-xluhG8Tu0mS0yQIUejkEZNHS4oM8cliQ&s"
                ), SubItem(
                    title = "Forest Retreat",
                    desc = "A quiet retreat in the heart of the forest.",
                    imageUrl = "https://images.pexels.com/photos/457876/pexels-photo-457876.jpeg?cs=srgb&dl=pexels-asadphoto-457876.jpg&fm=jpg"
                ),
                SubItem(
                    title = "Nature's Wonder",
                    desc = "A magnificent view of nature's beauty.",
                    imageUrl = "https://images.pexels.com/photos/2662116/pexels-photo-2662116.jpeg?cs=srgb&dl=pexels-jaime-reimer-1376930-2662116.jpg&fm=jpg"
                ), SubItem(
                    title = "Lakeside Calm",
                    desc = "A peaceful view of a lakeside at dusk.",
                    imageUrl = "https://thumbs.dreamstime.com/b/beautiful-view-nature-mountains-near-konigssee-lake-bavaria-germany-blue-sky-clouds-97444419.jpg"
                ), SubItem(
                    title = "Mountain View",
                    desc = "A beautiful view of the mountains near the lake.",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIA-xluhG8Tu0mS0yQIUejkEZNHS4oM8cliQ&s"
                ), SubItem(
                    title = "Sunset Bliss",
                    desc = "An amazing sunset over the mountains.",
                    imageUrl = "https://images.pexels.com/photos/457876/pexels-photo-457876.jpeg?cs=srgb&dl=pexels-asadphoto-457876.jpg&fm=jpg"
                ), SubItem(
                    title = "Forest Path",
                    desc = "A serene path through a dense forest.",
                    imageUrl = "https://images.unsplash.com/photo-1719297523639-b104d382c497?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ), SubItem(
                    title = "Riverbank Serenity",
                    desc = "A quiet riverbank with lush vegetation.",
                    imageUrl = "https://images.unsplash.com/photo-1719297523639-b104d382c497?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ),
                SubItem(
                    title = "Riverbank Serenity",
                    desc = "A quiet riverbank with lush vegetation.",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIA-xluhG8Tu0mS0yQIUejkEZNHS4oM8cliQ&s"
                ), SubItem(
                    title = "Forest Canopy",
                    desc = "A view of the forest canopy from below.",
                    imageUrl = "https://images.pexels.com/photos/457876/pexels-photo-457876.jpeg?cs=srgb&dl=pexels-asadphoto-457876.jpg&fm=jpg"
                ), SubItem(
                    title = "Mountain Range",
                    desc = "A wide view of a mountain range.",
                    imageUrl = "https://images.pexels.com/photos/2662116/pexels-photo-2662116.jpeg?cs=srgb&dl=pexels-jaime-reimer-1376930-2662116.jpg&fm=jpg"
                ), SubItem(
                    title = "Lakeside Cabin",
                    desc = "A cozy cabin by the lake.",
                    imageUrl = "https://img.freepik.com/free-photo/beautiful-natural-view-landscape_23-2150787996.jpg"
                ), SubItem(
                    title = "Autumn Path",
                    desc = "A path covered with autumn leaves.",
                    imageUrl = "https://thumbs.dreamstime.com/b/beautiful-view-nature-mountains-near-konigssee-lake-bavaria-germany-blue-sky-clouds-97444419.jpg"
                ), SubItem(
                    title = "Mist Over Lake",
                    desc = "Morning mist over a calm lake.",
                    imageUrl = "https://images.unsplash.com/photo-1719297523639-b104d382c497?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )
            )
        ), MainItem(
            imageUrl = "https://img.freepik.com/free-photo/beautiful-natural-view-landscape_23-2150787996.jpg",
            list = listOf(
                SubItem(
                    title = "Nature's Wonder",
                    desc = "A magnificent view of nature's beauty.",
                    imageUrl = "https://images.pexels.com/photos/2662116/pexels-photo-2662116.jpeg?cs=srgb&dl=pexels-jaime-reimer-1376930-2662116.jpg&fm=jpg"
                ), SubItem(
                    title = "Lakeside Calm",
                    desc = "A peaceful view of a lakeside at dusk.",
                    imageUrl = "https://thumbs.dreamstime.com/b/beautiful-view-nature-mountains-near-konigssee-lake-bavaria-germany-blue-sky-clouds-97444419.jpg"
                ), SubItem(
                    title = "Mountain View",
                    desc = "A beautiful view of the mountains near the lake.",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIA-xluhG8Tu0mS0yQIUejkEZNHS4oM8cliQ&s"
                ), SubItem(
                    title = "Sunset Bliss",
                    desc = "An amazing sunset over the mountains.",
                    imageUrl = "https://images.pexels.com/photos/457876/pexels-photo-457876.jpeg?cs=srgb&dl=pexels-asadphoto-457876.jpg&fm=jpg"
                ), SubItem(
                    title = "Forest Path",
                    desc = "A serene path through a dense forest.",
                    imageUrl = "https://images.unsplash.com/photo-1719297523639-b104d382c497?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ), SubItem(
                    title = "Riverbank Serenity",
                    desc = "A quiet riverbank with lush vegetation.",
                    imageUrl = "https://images.unsplash.com/photo-1719297523639-b104d382c497?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )
            )
        ), MainItem(
            imageUrl = "https://images.unsplash.com/photo-1719297523639-b104d382c497?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            list = listOf(
                SubItem(
                    title = "Sunlit Forest",
                    desc = "Sunlight filtering through the trees.",
                    imageUrl = "https://img.freepik.com/free-photo/beautiful-natural-view-landscape_23-2150787996.jpg"
                ), SubItem(
                    title = "Majestic Mountains",
                    desc = "Snow-capped peaks under a clear sky.",
                    imageUrl = "https://thumbs.dreamstime.com/b/beautiful-view-nature-mountains-near-konigssee-lake-bavaria-germany-blue-sky-clouds-97444419.jpg"
                ), SubItem(
                    title = "Peaceful Waters",
                    desc = "A tranquil lake reflecting the sky.",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIA-xluhG8Tu0mS0yQIUejkEZNHS4oM8cliQ&s"
                ), SubItem(
                    title = "Mountain Sunset",
                    desc = "The sun setting behind the mountains.",
                    imageUrl = "https://images.pexels.com/photos/457876/pexels-photo-457876.jpeg?cs=srgb&dl=pexels-asadphoto-457876.jpg&fm=jpg"
                ), SubItem(
                    title = "Winding Path",
                    desc = "A path winding through a green forest.",
                    imageUrl = "https://images.pexels.com/photos/2662116/pexels-photo-2662116.jpeg?cs=srgb&dl=pexels-jaime-reimer-1376930-2662116.jpg&fm=jpg"
                ), SubItem(
                    title = "Sunrise Over Hills",
                    desc = "Sunrise view over rolling hills.",
                    imageUrl = "https://img.freepik.com/free-photo/beautiful-natural-view-landscape_23-2150787996.jpg"
                ), SubItem(
                    title = "Autumn Glow",
                    desc = "Autumn foliage glowing in the sunlight.",
                    imageUrl = "https://thumbs.dreamstime.com/b/beautiful-view-nature-mountains-near-konigssee-lake-bavaria-germany-blue-sky-clouds-97444419.jpg"
                )
            )
        ), MainItem(
            imageUrl = "https://thumbs.dreamstime.com/b/beautiful-view-nature-mountains-near-konigssee-lake-bavaria-germany-blue-sky-clouds-97444419.jpg",
            list = listOf(
                SubItem(
                    title = "Riverbank Serenity",
                    desc = "A quiet riverbank with lush vegetation.",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIA-xluhG8Tu0mS0yQIUejkEZNHS4oM8cliQ&s"
                ), SubItem(
                    title = "Forest Canopy",
                    desc = "A view of the forest canopy from below.",
                    imageUrl = "https://images.pexels.com/photos/457876/pexels-photo-457876.jpeg?cs=srgb&dl=pexels-asadphoto-457876.jpg&fm=jpg"
                ), SubItem(
                    title = "Mountain Range",
                    desc = "A wide view of a mountain range.",
                    imageUrl = "https://images.pexels.com/photos/2662116/pexels-photo-2662116.jpeg?cs=srgb&dl=pexels-jaime-reimer-1376930-2662116.jpg&fm=jpg"
                ), SubItem(
                    title = "Lakeside Cabin",
                    desc = "A cozy cabin by the lake.",
                    imageUrl = "https://img.freepik.com/free-photo/beautiful-natural-view-landscape_23-2150787996.jpg"
                ), SubItem(
                    title = "Autumn Path",
                    desc = "A path covered with autumn leaves.",
                    imageUrl = "https://thumbs.dreamstime.com/b/beautiful-view-nature-mountains-near-konigssee-lake-bavaria-germany-blue-sky-clouds-97444419.jpg"
                ), SubItem(
                    title = "Mist Over Lake",
                    desc = "Morning mist over a calm lake.",
                    imageUrl = "https://images.unsplash.com/photo-1719297523639-b104d382c497?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                )
            )
        ), MainItem(
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIA-xluhG8Tu0mS0yQIUejkEZNHS4oM8cliQ&s",
            list = listOf(
                SubItem(
                    title = "Forest Creek",
                    desc = "A small creek flowing through the forest.",
                    imageUrl = "https://images.unsplash.com/photo-1719297523639-b104d382c497?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                ), SubItem(
                    title = "Rocky Shore",
                    desc = "A rocky shoreline with clear waters.",
                    imageUrl = "https://thumbs.dreamstime.com/b/beautiful-view-nature-mountains-near-konigssee-lake-bavaria-germany-blue-sky-clouds-97444419.jpg"
                ), SubItem(
                    title = "Sunset Reflections",
                    desc = "Sunset reflecting off the water.",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIA-xluhG8Tu0mS0yQIUejkEZNHS4oM8cliQ&s"
                ), SubItem(
                    title = "Mountain Mist",
                    desc = "Mist rising over the mountains.",
                    imageUrl = "https://images.pexels.com/photos/457876/pexels-photo-457876.jpeg?cs=srgb&dl=pexels-asadphoto-457876.jpg&fm=jpg"
                ), SubItem(
                    title = "Forest Walk",
                    desc = "A walk through a dense forest.",
                    imageUrl = "https://images.pexels.com/photos/2662116/pexels-photo-2662116.jpeg?cs=srgb&dl=pexels-jaime-reimer-1376930-2662116.jpg&fm=jpg"
                )
            )
        )
    )

    private val _listFlow = MutableStateFlow(items)
    private val _selectedMainItemIndex = MutableStateFlow(0)
    private val _searchQueryFlow = MutableStateFlow("")
    private val _bottomSheetItemsFlow = _listFlow.map { mainItems ->
        mainItems.map { mainItem ->
            BottomSheetItem(
                mainItem.list.size,
                mainItem.list.map { it.title.lowercase().toList() }.flatten()
                    .groupingBy { it.toString() }.eachCount()
                    .entries.sortedByDescending { it.value }.take(3)
                    .associate { it.key to it.value }
            )
        }
    }

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