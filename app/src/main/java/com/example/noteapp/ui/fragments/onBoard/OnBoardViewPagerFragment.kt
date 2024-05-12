package com.example.noteapp.ui.fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardViewPagerBinding

class OnBoardViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardViewPagerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOnBoardViewPagerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() = with(binding){

        when(requireArguments().getInt(ARG_ONBOARD_POSITION)){
            0 -> {
                onTxt.text="Очень удобный функционал"
            }
            1 -> {
                onTxt.text="Быстрый, качественный продукт"
            }
            2 -> {
                onTxt.text="Куча функций и интересных фишек"
            }
        }


    }

    companion object{
        const val ARG_ONBOARD_POSITION = "onBoard"

    }

}