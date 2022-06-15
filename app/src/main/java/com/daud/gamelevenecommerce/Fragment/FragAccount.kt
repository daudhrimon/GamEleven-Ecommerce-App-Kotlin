package com.daud.gamelevenecommerce.Fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Helper.DbHelper
import com.daud.gamelevenecommerce.Model.UserModel
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.SharedPref
import com.google.android.material.bottomsheet.BottomSheetDialog

class FragAccount : Fragment() {
    private lateinit var userData: UserModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.frag_account, container, false)

        initial()

        getData()

        val backBtn: ImageButton = view.findViewById(R.id.accountBack)
        backBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            backBtnClickHandler()

        })

        val ordersLay: RelativeLayout = view.findViewById(R.id.ordersLay)
        ordersLay.setOnClickListener(View.OnClickListener { view1: View? ->
            ordersLayClickHandler()
        })

        val profileLay: RelativeLayout = view.findViewById(R.id.profileLay)
        profileLay.setOnClickListener(View.OnClickListener { view1: View? ->
            profileLayClickHandler()
        })

        val addressLay: RelativeLayout = view.findViewById(R.id.addressLay)
        addressLay.setOnClickListener(View.OnClickListener { view1: View? ->
            addressNullBtmSheet()
        })

        val cngLanLay: RelativeLayout = view.findViewById(R.id.cngLanLay)
        cngLanLay.setOnClickListener(View.OnClickListener { view1: View? ->
            cngLanLayClickHandler()
        })

        return view
    }

    private fun getData() {
        val dbHelper = context?.let { DbHelper(it) }
        val sharedPref = SharedPref()
        context?.let { sharedPref.init(it) }
        userData = dbHelper?.getUserData(sharedPref.ID())!!
        /////////////////
        println(userData)
    }

    private fun cngLanLayClickHandler() {
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.alertdialog_language, null, false)
        val cancelBtn = view.findViewById<TextView>(R.id.cancelBtn)
        builder.setView(view)
        val dialog: Dialog = builder.create()
        dialog.show()
        cancelBtn.setOnClickListener { view1: View? -> dialog.dismiss() }
    }

    private fun addressNullBtmSheet() {
        val btmSheet = context?.let { BottomSheetDialog(it, R.style.AppBottomSheetDialogTheme) }
        btmSheet?.setContentView(R.layout.btmsheet_no_address)
        val backBtn = btmSheet?.findViewById<ImageButton>(R.id.addressBack)
        val plusBtn = btmSheet?.findViewById<ImageButton>(R.id.addressPlus)
        btmSheet?.show()
        backBtn!!.setOnClickListener { view: View? -> btmSheet.dismiss() }
        plusBtn!!.setOnClickListener { view: View? ->
            addressAddingBtmSheet()
            btmSheet.dismiss()
        }
    }

    private fun addressAddingBtmSheet() {
        val btmDialog = context?.let { BottomSheetDialog(it, R.style.AppBottomSheetDialogTheme) }
        btmDialog?.setContentView(R.layout.btmsheet_address_full)
        btmDialog?.show()
    }

    private fun profileLayClickHandler() {
        parentFragmentManager.beginTransaction().replace(R.id.mainFrame, FragProfile())
            .addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
    }

    private fun ordersLayClickHandler() {
        parentFragmentManager.beginTransaction().replace(R.id.mainFrame, FragOrderList())
            .addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
    }

    private fun backBtnClickHandler() {
        parentFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .replace(R.id.mainFrame, FragHome()).commit()
    }

    private fun initial() {
        MainActivity.btmCard.visibility = View.VISIBLE
        MainActivity.fab.visibility = View.VISIBLE
        ///////////////////////////////////////////
    }
}