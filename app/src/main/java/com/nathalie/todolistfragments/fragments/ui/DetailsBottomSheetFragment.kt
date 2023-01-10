package com.nathalie.todolistfragments.fragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nathalie.todolistfragments.R
import com.nathalie.todolistfragments.databinding.FragmentDetailsBottomSheetBinding

class DetailsBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentDetailsBottomSheetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

}