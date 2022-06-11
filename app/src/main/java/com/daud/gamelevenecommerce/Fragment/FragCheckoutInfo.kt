package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.daud.gamelevenecommerce.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragCheckoutInfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_checkout_info, container, false)

        val cOutBack: ImageButton = view.findViewById(R.id.cOutBack)
        cOutBack.setOnClickListener(View.OnClickListener { view1: View? ->
            cOutBackClickHandler()
        })

        val fullAdd: TextView = view.findViewById(R.id.fullAdd)
        fullAdd.setOnClickListener(View.OnClickListener { view1: View? ->
            fullAddBtmSheet()
        })

        val halfAdd: FloatingActionButton = view.findViewById(R.id.halfAdd)
        halfAdd.setOnClickListener(View.OnClickListener { view1: View? ->
            halfAddBtmSheet()
        })

        val addContact: FloatingActionButton = view.findViewById(R.id.addContact)
        addContact.setOnClickListener(View.OnClickListener { view1: View? ->
            addContactBtmSheet()
        })

        val orderBtn: LinearLayout = view.findViewById(R.id.orderBtn)
        orderBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            orderBtnClickHandler()
        })

        /////////////////////////////////////////
        val demo1: LinearLayout = view.findViewById(R.id.demo1)
        demo1.setOnClickListener(View.OnClickListener { view1: View? ->
            demoClick() })

        val demo2: LinearLayout = view.findViewById(R.id.demo2)
        demo2.setOnClickListener(View.OnClickListener { view1: View? ->
            demoClick() })

        return view
    }

    private fun addContactBtmSheet() {
        val btmSheet: BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        btmSheet.setContentView(R.layout.btmsheet_contact)
        btmSheet.show()
    }

    private fun orderBtnClickHandler() {
        parentFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragOrderDetails()).addToBackStack(null).commit()
    }

    private fun demoClick() {
        parentFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragProductDetails()).addToBackStack(null).commit()
    }

    private fun halfAddBtmSheet() {
        val btmSheet: BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        btmSheet.setContentView(R.layout.btmsheet_address_half)
        btmSheet.show()
    }

    private fun fullAddBtmSheet() {
        val btmSheet: BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        btmSheet.setContentView(R.layout.btmsheet_address_full)
        btmSheet.show()
    }

    private fun cOutBackClickHandler() {
        parentFragmentManager.popBackStack()
    }
}