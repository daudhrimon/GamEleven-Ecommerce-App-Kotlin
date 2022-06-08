package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Adapter.CateItemAdapter
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.Data

class FragCategory : Fragment() {
    companion object{
        val data = Data()
        lateinit var listItemRecycler: RecyclerView
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.frag_category, container, false)
        listItemRecycler = view.findViewById(R.id.listItemRecycler)

        categoryItemsDemo(view)



        return view
    }

    private fun categoryItemsDemo(view: View) {
        val cateItemRecycler: RecyclerView = view.findViewById(R.id.cateItemRecycler)
        cateItemRecycler.adapter = data.getCateItem()?.let { context?.let { it1 -> CateItemAdapter(it1, it) } }
    }
}