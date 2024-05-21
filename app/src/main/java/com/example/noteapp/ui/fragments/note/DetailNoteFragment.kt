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

        binding.btnAddText.setOnClickListener {
            val etTitle= binding.etTitle.text.toString()
            val etDesc= binding.etDescription.text.toString()

            App().getInstance()?.noteDao()?.insertNote(NoteModel(etTitle,etDesc))
            findNavController().navigateUp()
        }
    }


}