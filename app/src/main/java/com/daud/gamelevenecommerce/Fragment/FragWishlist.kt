package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Adapter.WishlistAdapter
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.Data

class FragWishlist : Fragment() {
    private lateinit var backBtn: ImageButton
    var data = Data()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_wishlist, container, false)

        initial(view)

        setWishlistDemo(view)

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

    private fun setWishlistDemo(view: View) {
        val wishRecycler: RecyclerView = view.findViewById(R.id.wishRecycler)
        wishRecycler.adapter = context?.let { data.getWishlist()?.let { it1 -> WishlistAdapter(it, it1) } }
    }

    private fun initial(view: View) {
        MainActivity.btmCard.visibility = View.VISIBLE
        MainActivity.fab.visibility = View.VISIBLE
        ////////////////////////////////////////////
        backBtn = view.findViewById(R.id.wishBack)
    }
}