package com.daud.gamelevenecommerce.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daud.gamelevenecommerce.Fragment.FragOrderAll
import com.daud.gamelevenecommerce.Fragment.FragOrderCancel
import com.daud.gamelevenecommerce.Fragment.FragOrderDelivered
import com.daud.gamelevenecommerce.Fragment.FragOrderPending

class OrderListAdapter(fragmentManager: FragmentManager,lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> {FragOrderAll()}
            1-> {FragOrderPending()}
            2-> {FragOrderDelivered()}
            3-> {FragOrderCancel()}
            else -> {Fragment()}
        }
    }
}