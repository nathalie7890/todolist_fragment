package com.nathalie.todolistfragments.fragments.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.nathalie.todolistfragments.MyApplication
import com.nathalie.todolistfragments.data.Model.Task
import com.nathalie.todolistfragments.databinding.FragmentDetailsBinding
import com.nathalie.todolistfragments.databinding.FragmentEditBinding
import com.nathalie.todolistfragments.viewModels.DetailsViewModel
import com.nathalie.todolistfragments.viewModels.EditViewModel

class EditFragment : Fragment() {
    private lateinit var binding: FragmentEditBinding
    val viewModel: EditViewModel by viewModels {
        EditViewModel.Provider((requireContext().applicationContext as MyApplication).taskRepo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navArgs: EditFragmentArgs by navArgs()

        viewModel.getTaskById(navArgs.id)
        viewModel.task.observe(viewLifecycleOwner) {
            binding.etTitle.setText(it.title)
            binding.etDate.setText(it.date)
            binding.etDetail.setText(it.details)
        }

        binding.btnSave.setOnClickListener {
            val id = navArgs.id
            val title = binding.etTitle.text.toString()
            val date = binding.etDate.text.toString()
            val details = binding.etDetail.text.toString()

            val task = Task(id, title, date, details, 1 )
            viewModel.editTask(id, task)
            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_edit", bundle)
            NavHostFragment.findNavController(this).popBackStack()
            NavHostFragment.findNavController(this).popBackStack()
        }
    }
}