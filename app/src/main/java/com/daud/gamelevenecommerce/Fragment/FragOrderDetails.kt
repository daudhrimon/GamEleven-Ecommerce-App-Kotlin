package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.daud.gamelevenecommerce.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class FragOrderDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_order_details, container, false)

        val backBtn: ImageButton = view.findViewById(R.id.odBack)
        backBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            backBtnClickHandler()
        })

        val odDemo1: LinearLayout = view.findViewById(R.id.odDemo1)
        odDemo1.setOnClickListener(View.OnClickListener { view1: View? ->
            demoClick()
        })

        val odDemo2: LinearLayout = view.findViewById(R.id.odDemo2)
        odDemo2.setOnClickListener(View.OnClickListener { view1: View? ->
            demoClick()
        })

        val unpaidBtn: LinearLayout = view.findViewById(R.id.unpaidBtn)
        unpaidBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            unpaidBtnClickHandler()
        })



        return view
    }

    private fun unpaidBtnClickHandler() {
        val btmSheet:BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        btmSheet.setContentView(R.layout.btmsheet_payment)
        val paymentBtn = btmSheet.findViewById<LinearLayout>(R.id.paymentBtn)
        btmSheet.show()

        paymentBtn!!.setOnClickListener { view: View? -> btmSheet.dismiss() }
    }

    private fun demoClick() {
        parentFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragProductDetails()).addToBackStack(null).commit()
    }

    private fun backBtnClickHandler() {
        parentFragmentManager.popBackStack()
    }
}