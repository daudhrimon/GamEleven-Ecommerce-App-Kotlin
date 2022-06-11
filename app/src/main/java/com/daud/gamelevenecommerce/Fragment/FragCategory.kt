package com.daud.gamelevenecommerce.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Adapter.CateItemAdapter
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.Data

class FragCategory : Fragment() {
    companion object{
        lateinit var listItemRecycler: RecyclerView
        val data = Data()
    }
    private lateinit var backBtn: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.frag_category, container, false)

        listItemRecycler = view.findViewById(R.id.listItemRecycler)

        initial(view)

        categoryItemsDemo(view)

        backBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            backBtnClickHandler()
        })

        return view
    }

    private fun backBtnClickHandler() {
        parentFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .replace(R.id.mainFrame, FragHome()).commit()
    }

    private fun categoryItemsDemo(view: View) {
        val cateItemRecycler: RecyclerView = view.findViewById(R.id.cateItemRecycler)
        cateItemRecycler.adapter = data.getCateItem()?.let { context?.let { it1 -> CateItemAdapter(it1, it) } }
    }

    private fun initial(view: View) {
        MainActivity.btmCard.visibility = View.VISIBLE
        MainActivity.fab.visibility = View.VISIBLE
        ///////////////////////////////////////////
        backBtn = view.findViewById(R.id.cateBack)
    }
}