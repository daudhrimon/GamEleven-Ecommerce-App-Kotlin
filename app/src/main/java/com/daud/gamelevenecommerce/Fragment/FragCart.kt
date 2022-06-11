package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Adapter.CartAdapter
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.Data

class FragCart : Fragment() {
    var data = Data()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_cart, container, false)

        initial(view)

        setDemoCarts(view)

        return view
    }

    private fun setDemoCarts(view: View) {
        val cartRecycler: RecyclerView = view.findViewById(R.id.cartRecycler)
        cartRecycler.adapter = data.getCart()?.let { context?.let { it1 -> CartAdapter(it1, it) } }
    }

    private fun initial(view: View) {
        MainActivity.btmCard.visibility = View.VISIBLE
        MainActivity.fab.visibility = View.VISIBLE
        ////////////////////////////////////////////
    }
}