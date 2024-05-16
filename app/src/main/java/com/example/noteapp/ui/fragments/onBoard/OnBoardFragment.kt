package com.example.noteapp.ui.fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.adapter.OnBoardViewPagerAdapter


class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setupListener()
    }


    private fun init() {
        binding.viewPager2.adapter= OnBoardViewPagerAdapter(this@OnBoardFragment)


    }


    private fun setupListener() = with(binding.viewPager2) {

        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position ==2 ){
                    binding.skipTxt.visibility = View.INVISIBLE
                }else{
                    binding.skipTxt.visibility = View.VISIBLE
                }
            }
        })
        binding.skipTxt.setOnClickListener{
            if (currentItem < 3){
                setCurrentItem(currentItem + 2 , true)
            }
        }

        registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2 ){
                    binding.startTxt.visibility = View.VISIBLE
                }else{
                    binding.startTxt.visibility = View.INVISIBLE
                }
            }
        })


        val adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
        binding.viewPager2.adapter = adapter
        binding.dots.attachTo(binding.viewPager2)


        binding.startTxt.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
        }

    }
}