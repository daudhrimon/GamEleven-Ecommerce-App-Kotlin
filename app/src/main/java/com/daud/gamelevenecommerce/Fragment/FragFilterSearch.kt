package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Adapter.AdsAdapter
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.Data

class FragFilterSearch : Fragment() {
    val data = Data()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_filter_search, container, false)

        initial(view)

        setDemoData(view)

        return view
    }

    private fun setDemoData(view: View) {
        val filterRecycler: RecyclerView = view.findViewById(R.id.filterRecycler)
        filterRecycler.adapter = data.getAds()?.let { context?.let { it1 -> AdsAdapter(it1, it) } }
    }

    private fun initial(view: View) {
        MainActivity.btmCard.visibility = View.GONE
        MainActivity.fab.visibility = View.GONE
        //////////////////////////////////////////
    }
}