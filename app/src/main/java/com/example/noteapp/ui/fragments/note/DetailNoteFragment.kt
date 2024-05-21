package com.example.noteapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.extension.setBackStackData
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.FragmentDetailNoteBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class DetailNoteFragment : Fragment() {

    private lateinit var binding: FragmentDetailNoteBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailNoteBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {

        val date = SimpleDateFormat("dd MMMM", Locale("ru")).format(Date())
        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        binding.tvTimeMonth.text = date.toString()
        binding.tvTimeHours.text = time.toString()

        binding.btnAddText.setOnClickListener {
            val etTitle= binding.etTitle.text.toString()
            val etDesc= binding.etDescription.text.toString()


            App().getInstance()?.noteDao()?.insertNote(NoteModel(etTitle,etDesc, date, time))
            findNavController().navigateUp()
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }


}