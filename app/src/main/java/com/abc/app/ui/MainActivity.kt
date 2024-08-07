package com.abc.app.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.abc.app.adapter.ImageAdapter
import com.abc.app.adapter.ItemAdapter
import com.abc.app.databinding.ActivityMainBinding
import com.abc.app.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                homeViewModel.setPageIndex(position)
            }
        })
        binding.searchBar.addTextChangedListener {
            homeViewModel.setSearch(it.toString())
        }
        binding.content.subList.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            homeViewModel.uiStateFlow.collect { uiState ->
                if (binding.viewPager.adapter == null && uiState.items.isNotEmpty())
                    binding.viewPager.adapter = ImageAdapter(uiState.items)
                binding.content.subList.adapter = ItemAdapter(uiState.subItemList)
            }
        }

        binding.fab.setOnClickListener { _ ->
            val sheet = ModalBottomSheetDialog()
            sheet.show(supportFragmentManager, ModalBottomSheetDialog.TAG)
        }
    }
}

