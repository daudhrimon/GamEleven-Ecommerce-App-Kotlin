package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.daud.gamelevenecommerce.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class FragProfile : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_profile, container, false)

        val backBtn: ImageButton = view.findViewById(R.id.profileBack)
        backBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            backBtnClickHandler()
        })

        val personalEd: TextView = view.findViewById(R.id.personalEd)
        personalEd.setOnClickListener(View.OnClickListener { view1: View? ->
            personalEdClickHandler()
        })

        val emailEd: TextView = view.findViewById(R.id.emailEd)
        emailEd.setOnClickListener(View.OnClickListener { view1: View? ->
            emailEdClickHandler()
        })

        return view
    }

    private fun emailEdClickHandler() {
        val btmSheet: BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        btmSheet.setContentView(R.layout.btmsheet_email)
        btmSheet.show()
    }

    private fun personalEdClickHandler() {
        val btmSheet: BottomSheetDialog = context?.let { BottomSheetDialog(it, R.style.AppBottomSheetDialogTheme) }!!
        btmSheet.setContentView(R.layout.btmsheet_personalinfo)
        btmSheet.show()
    }

    private fun backBtnClickHandler() {
        parentFragmentManager.popBackStack()
    }
}