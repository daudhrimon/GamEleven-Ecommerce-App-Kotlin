package com.daud.gamelevenecommerce.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.R

class FragSearchFilter : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_search_filter, container, false)

        initial(view)

        return view
    }

    private fun initial(view: View) {
        MainActivity.btmCard.visibility = View.GONE
        MainActivity.fab.visibility = View.GONE
        //////////////////////////////////////////
    }
}