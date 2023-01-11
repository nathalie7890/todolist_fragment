package com.nathalie.todolistfragments.fragments.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nathalie.todolistfragments.MyApplication
import com.nathalie.todolistfragments.R
import com.nathalie.todolistfragments.adapters.TaskAdapter
import com.nathalie.todolistfragments.databinding.FragmentHomeBinding
import com.nathalie.todolistfragments.viewModels.HomeViewModel
import com.nathalie.todolistfragments.viewModels.MainViewModel

class HomeFragment : Fragment() {
    private lateinit var adapter: TaskAdapter
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel.tasks.observe(requireActivity()) { tasks ->
//            adapter = TaskAdapter(tasks)
//            val layoutManager = LinearLayoutManager(requireActivity())
//            binding.rvItems.adapter = adapter
//            binding.rvItems.layoutManager = layoutManager
//        }

        setUpAdapter()

        binding.efabAddNewItem.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeToAddItem()
            NavHostFragment.findNavController(this).navigate(action)
        }

        viewModel.tasks.observe(viewLifecycleOwner) {
            adapter.setTasks(it)
        }


        setFragmentResultListener("from_details") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getTasks()
            }
        }

        setFragmentResultListener("from_edit") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getTasks()
            }
        }

        setFragmentResultListener("from_add_item") { _, result ->
            val refresh = result.getBoolean("refresh")
            if (refresh) {
                viewModel.getTasks()
            }
        }

        setFragmentResultListener("from_image_gallery") { _, result ->
            val msg = result.getString("greeting")
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        }
    }

    fun setUpAdapter() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = TaskAdapter(
            emptyList(),
            {
                val action = HomeFragmentDirections.actionHomeToDetails(it.id!!)
                NavHostFragment.findNavController(this).navigate(action)
            },
            {
                val detailsFragment = DetailsBottomSheetFragment(it)
                detailsFragment.show(childFragmentManager, "Child-Fragment")
            },
            { view, task ->
                val popupMenu = PopupMenu(requireContext(), view)
                popupMenu.setOnMenuItemClickListener {
                    return@setOnMenuItemClickListener when (it.itemId) {
                        R.id.action1 -> {
                            Log.d("debugging", "Action 1: ${task.title}")
                            true
                        }

                        R.id.action2 -> {
                            Log.d("debugging", "Action 2: ${task.title}")
                            true
                        }

                        R.id.action3 -> {
                            Log.d("debugging", "Action 3: ${task.title}")
                            true
                        }

                        else -> false
                    }
                }

                popupMenu.inflate(R.menu.task_actions)
                popupMenu.setForceShowIcon(true)
                popupMenu.show()
            }
        )

        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = layoutManager
    }
}