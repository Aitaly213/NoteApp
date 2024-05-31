package com.example.noteapp.ui.fragments.note

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
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

    private var noteId: Int = -1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        update()
        setupListeners()
    }

    private fun update() {
        arguments?.let {
            noteId = it.getInt("noteId", -1)
        }
        if (noteId != -1) {
            val args = App().getInstance()?.noteDao()?.getNoteById(noteId)
            args?.let { model ->
                binding.etTitle.setText(model.title)
                binding.etDescription.setText(model.description)

                val colorResource = model.color
                when (colorResource) {
                    R.drawable.style -> binding.radioWhite.isChecked = true
                    R.drawable.style_white -> binding.radioWhite.isChecked = true
                    R.drawable.style_brown -> binding.radioBrown.isChecked = true
                }

            }
        }


    }

    private fun setupListeners() {

        var color = 0

        binding.btnBlack.setOnClickListener {
            binding.radioBlack.isChecked = true
            color = 0
        }
        binding.btnWhite.setOnClickListener {
            binding.radioWhite.isChecked = true
            color = 1
        }
        binding.btnBrown.setOnClickListener {
            binding.radioBrown.isChecked = true
            color = 2
        }

        val date = SimpleDateFormat("dd MMMM", Locale("ru")).format(Date())
        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        binding.tvTimeMonth.text = date.toString()
        binding.tvTimeHours.text = time.toString()

        binding.btnAddText.setOnClickListener {
            val etTitle = binding.etTitle.text.toString()
            val etDesc = binding.etDescription.text.toString()
            if (noteId != -1) {
                val updateNote = NoteModel(etTitle, etDesc, date, time, color)
                updateNote.id = noteId
                App().getInstance()?.noteDao()?.updateNote(updateNote)
            } else {
                App().getInstance()?.noteDao()
                    ?.insertNote(NoteModel(etTitle, etDesc, date, time, color))
            }

            findNavController().navigateUp()
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }


    }


}