package com.example.noteapp.ui.fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardViewPagerBinding
import com.example.noteapp.ui.fragments.note.NoteFragment
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

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
                lottieAnimation.setAnimation(R.raw.lottie_1)
                startTxt.visibility= View.INVISIBLE
            }
            1 -> {
                onTxt.text="Быстрый, качественный продукт"
                lottieAnimation.setAnimation(R.raw.lottie_2)
                startTxt.visibility= View.INVISIBLE
            }
            2 -> {
                onTxt.text="Куча функций и интересных фишек"
                lottieAnimation.setAnimation(R.raw.lottie_3)
                startTxt.visibility= View.VISIBLE


            }
        }

        startTxt.setOnClickListener {
            val fragment = NoteFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()


        }


    }

    companion object{
        const val ARG_ONBOARD_POSITION = "onBoard"

    }

}