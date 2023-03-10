package com.nathalie.todolistfragments.fragments.ui

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.nathalie.todolistfragments.data.Model.Task
import com.nathalie.todolistfragments.MyApplication
import com.nathalie.todolistfragments.R
import com.nathalie.todolistfragments.databinding.FragmentAddItemBinding
import com.nathalie.todolistfragments.viewModels.AddTaskViewModel
import java.io.File

class AddItemFragment : Fragment() {
    private lateinit var filePickerLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentAddItemBinding
    private var bytes: ByteArray? = null
    private val viewModel: AddTaskViewModel by viewModels {
        AddTaskViewModel.Provider(
            (requireContext().applicationContext as MyApplication).taskRepo
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filePickerLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            it?.let { uri ->
                binding.tvImageName.text = requireContext().contentResolver.getFileName(uri)

                val inputStream = requireContext().contentResolver.openInputStream(uri)
                bytes = inputStream?.readBytes()
                inputStream?.close()

            }
        }

        binding.tvChooseImage.setOnClickListener {
            filePickerLauncher.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val date = binding.etDate.text.toString()
            val details = binding.etDetails.text.toString()
            viewModel.addTask(Task(null, title, date, details, 0, bytes))

            val bundle = Bundle()
            bundle.putBoolean("refresh", true)
            setFragmentResult("from_add_item", bundle)
            NavHostFragment.findNavController(this).popBackStack()
        }
    }

    private fun ContentResolver.getFileName(fileUri: Uri): String {
        val cursor = this.query(fileUri, null, null, null, null)

        cursor?.let {
            val name = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            return cursor.getString(name)
        }
        cursor?.close()

        return "Unknown"
    }
}

