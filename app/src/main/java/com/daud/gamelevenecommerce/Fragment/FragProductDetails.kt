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
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.Data
import com.denzcoskun.imageslider.ImageSlider
import com.google.android.material.bottomsheet.BottomSheetDialog

class FragProductDetails : Fragment() {
    val data = Data()
    private lateinit var desBtn: LinearLayout
    private lateinit var specBtn: LinearLayout
    private lateinit var desTv: TextView
    private lateinit var specTv: TextView
    private lateinit var buyBtn: LinearLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_product_details, container, false)

        initial(view)

        setImageSlider(view)

        val backBtn: ImageButton = view.findViewById(R.id.proDetailsBack)
        backBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            backBtnClickHandler()
        })

        desTv.setOnClickListener(View.OnClickListener { view1: View? ->
            setBtnColor(desBtn,desTv,specBtn,specTv)
        })

        specBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            setBtnColor(specBtn,specTv,desBtn,desTv)
        })

        buyBtn.setOnClickListener(View.OnClickListener {view1: View? ->
            buyBtnClickHandler()
        })

        return view
    }

    private fun backBtnClickHandler() {
        parentFragmentManager.popBackStack()
    }

    private fun buyBtnClickHandler() {
        val btmSheet:BottomSheetDialog = context?.let { BottomSheetDialog(it, R.style.AppBottomSheetDialogTheme) }!!
        btmSheet.setContentView(R.layout.btmsheet_single_product)
        val spCOut = btmSheet.findViewById<LinearLayout>(R.id.spCOut)
        btmSheet.show()

        spCOut!!.setOnClickListener { view: View? ->
            parentFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.mainFrame, FragCheckoutInfo()).addToBackStack(null).commit()
            btmSheet.dismiss()
        }
    }

    private fun setBtnColor(firstLay: LinearLayout, firstTv: TextView, secLay: LinearLayout, secTv: TextView) {
        firstLay.setBackgroundColor(resources.getColor(R.color.selector_clr))
        firstTv.setTextColor(resources.getColor(R.color.white))
        secLay.setBackgroundColor(resources.getColor(R.color.white))
        secTv.setTextColor(resources.getColor(R.color.pd_btn_clr))
    }

    private fun setImageSlider(view: View) {
        val imageSliderPd: ImageSlider = view.findViewById(R.id.imageSliderPd)
        data.pdSlider()?.let { imageSliderPd.setImageList(it) }
    }

    private fun initial(view: View) {
        MainActivity.btmCard.visibility = View.GONE
        MainActivity.fab.visibility = View.GONE
        ///////////////////////////////////////////
        desBtn = view.findViewById(R.id.desBtn)
        specBtn = view.findViewById(R.id.specBtn)
        desTv = view.findViewById(R.id.desTv)
        specTv = view.findViewById(R.id.specTv)
        buyBtn = view.findViewById(R.id.buyBtn)
    }
}