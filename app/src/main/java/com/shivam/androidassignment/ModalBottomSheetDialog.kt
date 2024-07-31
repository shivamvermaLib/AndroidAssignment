package com.shivam.androidassignment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shivam.androidassignment.adapter.BottomSheetItemAdapter
import com.shivam.androidassignment.databinding.ItemBottomSheetBinding
import com.shivam.androidassignment.viewmodels.HomeViewModel
import kotlinx.coroutines.launch

class ModalBottomSheetDialog(private val viewModel: HomeViewModel) : BottomSheetDialogFragment() {
    private lateinit var binding: ItemBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemBottomSheetBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            viewModel.uiStateFlow.collect {
                binding.rcylBottomList.adapter = BottomSheetItemAdapter(it.bottomSheetItems)
            }
        }
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // used to show the bottom sheet dialog
        dialog?.setOnShowListener { it ->
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return super.onCreateDialog(savedInstanceState)
    }

    companion object {
        const val TAG = "ModalBottomSheetDialog"
    }
}