package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TableLayout
import androidx.viewpager2.widget.ViewPager2
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Adapter.OrderListAdapter
import com.daud.gamelevenecommerce.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FragOrderList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_order_list, container, false)

        initial(view)

        val backBtn = view.findViewById<ImageButton>(R.id.backOrder)
        backBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            backBtnClickHandler()
        })

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager2)
        viewPager2.adapter = OrderListAdapter(parentFragmentManager,lifecycle)

        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0-> tab.text = "ALL"
                1-> tab.text = "PENDING"
                2-> tab.text = "DELIVERED"
                3-> tab.text = "CANCEL"
            }
        }



        return view
    }

    private fun backBtnClickHandler() {
        parentFragmentManager.popBackStack()
    }

    private fun initial(view: View?) {
        MainActivity.fab.visibility = View.GONE
        MainActivity.btmCard.visibility = View.GONE
        ///////////////////////////////////////////
    }
}