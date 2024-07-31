package com.shivam.androidassignment

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.shivam.androidassignment.adapter.ImageAdapter
import com.shivam.androidassignment.adapter.ItemAdapter
import com.shivam.androidassignment.databinding.ActivityMainBinding
import com.shivam.androidassignment.viewmodels.HomeViewModel
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
//                super.onPageSelected(position)
                println("position = [${position}]")
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
            val sheet = ModalBottomSheetDialog(homeViewModel)
            sheet.show(supportFragmentManager, ModalBottomSheetDialog.TAG)
        }
    }
}

