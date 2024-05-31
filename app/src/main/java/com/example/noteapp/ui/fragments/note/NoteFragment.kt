package com.example.noteapp.ui.fragments.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginEnd
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.App
import com.example.noteapp.R
import com.example.noteapp.data.models.NoteModel
import com.example.noteapp.databinding.FragmentNoteBinding
import com.example.noteapp.interfaces.OnClickItem
import com.example.noteapp.ui.adapter.NoteAdapter


class NoteFragment : Fragment(), OnClickItem {

    private lateinit var binding: FragmentNoteBinding

    private val noteAdapter = NoteAdapter(this, this)

    private var isGridLayout: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        setupListeners()
        getData()
    }

    private fun initialization() {
        binding.rvNote.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteAdapter
        }
    }

    private fun setupListeners() = with(binding) {
        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_detailNoteFragment)
        }

        btnChangeLayout.setOnClickListener {
            toggleLayout()
        }
        btnChangeLayoutToLinear.setOnClickListener {
            toggleLayout()
        }


    }

    private fun getData() {
        App().getInstance()?.noteDao()?.getAll()?.observe(viewLifecycleOwner) {
            noteAdapter.submitList(it)
        }
    }

    override fun onLongClick(noteModel: NoteModel) {
        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle("Вы точно хотите удалить?")
            setPositiveButton("Да") { dialog, which ->
                App().getInstance()?.noteDao()?.deleteNote(noteModel)
            }
            setNegativeButton("Нет") { dialog, which ->
                dialog.cancel()
            }
            show()
        }

        builder.create()
    }

    private fun toggleLayout() {
        isGridLayout = !isGridLayout
        val layoutManager = if (isGridLayout) {
            binding.btnChangeLayout.visibility = View.GONE
            binding.btnChangeLayoutToLinear.visibility = View.VISIBLE
            GridLayoutManager(requireContext(), 2)
        } else {
            binding.btnChangeLayoutToLinear.visibility = View.GONE
            binding.btnChangeLayout.visibility = View.VISIBLE
            LinearLayoutManager(requireContext())
        }
        binding.rvNote.layoutManager = layoutManager
    }

    override fun onClick(noteModel: NoteModel) {
        val action = NoteFragmentDirections.actionNoteFragmentToDetailNoteFragment(noteModel.id)
        findNavController().navigate(action)

    }
}