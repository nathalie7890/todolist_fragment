package com.nathalie.todolistfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nathalie.todolistfragments.databinding.FragmentProfile2Binding
import com.nathalie.todolistfragments.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfile2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentProfile2Binding.inflate(layoutInflater)
        return binding.root
    }


}