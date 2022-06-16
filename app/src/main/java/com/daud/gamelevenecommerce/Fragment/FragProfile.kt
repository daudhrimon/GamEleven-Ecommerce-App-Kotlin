package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Helper.DbHelper
import com.daud.gamelevenecommerce.Model.UserModel
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.SharedPref
import com.google.android.material.bottomsheet.BottomSheetDialog

class FragProfile : Fragment() {
    private lateinit var userData: UserModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_profile, container, false)

        initial(view)

        // user info getting from database
        getData()

        // set user info
        setUserInfo(view)

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

    // set user info
    private fun setUserInfo(view: View?) {
        val pNameTv = view?.findViewById<TextView>(R.id.pNameTv)
        val piNameTv = view?.findViewById<TextView>(R.id.piNameTv)
        if (!userData.firstName.isEmpty()) {
            pNameTv?.text = userData.firstName + " " + userData.lastName
            piNameTv?.text = userData.firstName + " " + userData.lastName
        }

        val piContact = view?.findViewById<TextView>(R.id.piContact)
        if (!userData.phone.isEmpty()){
            piContact?.text = userData.phone
        }

        val piGender = view?.findViewById<TextView>(R.id.piGender)
        if (!userData.gender.isEmpty()){
            piGender?.text = userData.gender
        }

        val piBirthDate = view?.findViewById<TextView>(R.id.piBirthDate)
        if (!userData.birthDate.isEmpty()){
            piBirthDate?.text = userData.birthDate
        }

        val piEmail = view?.findViewById<TextView>(R.id.piEmail)
        if (!userData.email.isEmpty()){
            piEmail?.text = userData.email
        }
    }

    // get user data from database
    private fun getData() {
        val dbHelper = context?.let { DbHelper(it) }
        val sharedPref = SharedPref()
        context?.let { sharedPref.init(it) }
        userData = dbHelper?.getUserData(sharedPref.ID())!!
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

    private fun initial(view: View?) {
        MainActivity.fab.visibility = View.GONE
        MainActivity.btmCard.visibility = View.GONE
        ///////////////////////////////////////////
    }
}