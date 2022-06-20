package com.daud.gamelevenecommerce.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Adapter.OnBoardingAdapter
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.SharedPref

class FragOnBoarding : Fragment() {
    private lateinit var onbViewPager2: ViewPager2
    private lateinit var dot1: ImageView
    private lateinit var dot2: ImageView
    private lateinit var dot3: ImageView
    private lateinit var skipBtn: Button
    private var Position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_on_boarding, container, false)

        initial(view)

        var images = intArrayOf(R.drawable.onb_img_first, R.drawable.onb_img_sec, R.drawable.onb_img_third)
        var headers = intArrayOf(R.string.onb_header1, R.string.onb_header2, R.string.onb_header3)

        val adapter = OnBoardingAdapter(images,headers)

        onbViewPager2.adapter = adapter

        addDots(0)

        onbViewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                /*addDots(onbViewPager2.currentItem)
                Position = position*/
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                addDots(onbViewPager2.currentItem)
                Position = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                //addDots(onbViewPager2.currentItem)
                super.onPageScrollStateChanged(state)
            }

        })

        skipBtn.setOnClickListener { view1: View? -> skipBtnOnClick() }

        return view
    }

    private fun skipBtnOnClick() {
        if (Position < 2) {
            onbViewPager2.currentItem = Position+1
        } else {
            val sharedPref = SharedPref()
            sharedPref.init(requireContext())
            sharedPref.write("WELCOME","DONE")
            startActivity(Intent(requireContext(), MainActivity::class.java))
            activity?.finish()
        }
    }


    private fun addDots(position: Int) {
        when (position) {
            0 -> {
                dot1.setImageResource(R.drawable.dot_selected)
                dot2.setImageResource(R.drawable.dot_unselected)
                dot3.setImageResource(R.drawable.dot_unselected)
            }
            1 -> {
                dot1.setImageResource(R.drawable.dot_unselected)
                dot2.setImageResource(R.drawable.dot_selected)
                dot3.setImageResource(R.drawable.dot_unselected)
            }
            2 -> {
                dot1.setImageResource(R.drawable.dot_unselected)
                dot2.setImageResource(R.drawable.dot_unselected)
                dot3.setImageResource(R.drawable.dot_selected)
            }
            else -> {}
        }
    }

    private fun initial(view: View) {
        onbViewPager2 = view.findViewById(R.id.onbViewPager2)
        skipBtn = view.findViewById(R.id.skipBtn)
        dot1 = view.findViewById(R.id.dot1)
        dot2 = view.findViewById(R.id.dot2)
        dot3 = view.findViewById(R.id.dot3)
    }
}
