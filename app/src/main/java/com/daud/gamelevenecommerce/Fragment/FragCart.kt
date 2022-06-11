package com.daud.gamelevenecommerce.Fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Adapter.CartAdapter
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.Data

class FragCart : Fragment() {
    private lateinit var backBtn: ImageButton
    private lateinit var dltBtn: ImageButton
    var data = Data()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_cart, container, false)

        initial(view)

        setDemoCarts(view)

        backBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            backBtnClickHandler()
        })

        dltBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            cartDltClickHandler()
        })

        return view
    }

    private fun cartDltClickHandler() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.aleartdialog_delete, null)
        val cancelBtn = view.findViewById<Button>(R.id.cancelBtn)
        val okBtn = view.findViewById<Button>(R.id.okBtn)
        builder.setView(view)
        val dialog: Dialog = builder.create()
        dialog.show()
        okBtn.setOnClickListener { view1: View? -> dialog.dismiss() }
        cancelBtn.setOnClickListener { view1: View? -> dialog.dismiss() }
    }

    private fun backBtnClickHandler() {
        parentFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .replace(R.id.mainFrame, FragHome()).commit()
    }

    private fun setDemoCarts(view: View) {
        val cartRecycler: RecyclerView = view.findViewById(R.id.cartRecycler)
        cartRecycler.adapter = data.getCart()?.let { context?.let { it1 -> CartAdapter(it1, it) } }
    }

    private fun initial(view: View) {
        MainActivity.btmCard.visibility = View.VISIBLE
        MainActivity.fab.visibility = View.VISIBLE
        ////////////////////////////////////////////
        backBtn = view.findViewById(R.id.cartBack)
        dltBtn = view.findViewById(R.id.cartDlt)
    }
}