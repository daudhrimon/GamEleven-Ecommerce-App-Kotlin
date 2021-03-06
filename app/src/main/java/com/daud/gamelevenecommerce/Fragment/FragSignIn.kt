package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Helper.DbHelper
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.SharedPref
import com.daud.gamelevenecommerce.Util.Util

class FragSignIn : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_sign_in, container, false)

        initial(view)

        val sInBack = view.findViewById<ImageButton>(R.id.sInBack)
        sInBack.setOnClickListener(View.OnClickListener { view1: View? ->
            context?.let { Util.hideSoftKeyBoard(it,view) }
            backClickHandler()
        })

        val signUpBtn: Button = view.findViewById(R.id.signUpBtn)
        signUpBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            signUpBtnClickHandler()
        })

        val signInBtn: Button = view.findViewById(R.id.signInBtn)
        signInBtn.setOnClickListener(View.OnClickListener {view1: View? ->
            context?.let { Util.hideSoftKeyBoard(it,view) }
            signInBtnClickHandler(view)
        })

        // On Screen Click hide Keyboard
        view.setOnClickListener(View.OnClickListener { view1: View? ->
            onScreenClick(view)
        })

        return view
    }

    // On Screen Click hide Keyboard
    private fun onScreenClick(view: View) {
        context?.let { Util.hideSoftKeyBoard(it,view) }
    }

    private fun signInBtnClickHandler(view: View) {
        val dbHelper = context?.let { DbHelper(it) }
        val sInEmailEt: EditText = view.findViewById(R.id.sInEmailEt)
        val sInPassEt: EditText = view.findViewById(R.id.sInPassEt)

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(sInEmailEt?.text.toString()).matches()) {
            sInEmailEt.setError("Enter an email address")
            sInEmailEt.requestFocus()
            return
        }

        if (sInPassEt.getText().toString().length < 6) {
            sInPassEt.setError("Password is too Short")
            sInPassEt.requestFocus()
            return
        }

        // getting user id From Database
        val ID: Int? = dbHelper?.checkEmailAndPass(sInEmailEt.text.toString(), sInPassEt.text.toString())
        // checking the return value from database
        if (ID != -1) {
            val sharedPref = SharedPref()
            context?.let { sharedPref.init(it) }
            sharedPref.write("ID",ID.toString())
            sharedPref.write("SIGNIN","OK")
            parentFragmentManager.beginTransaction().setTransition(TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.mainFrame, FragAccount()).commit()
        } else {
            Toast.makeText(context,"Invalid Email or Password",Toast.LENGTH_SHORT).show()
        }
    }

    private fun signUpBtnClickHandler() {
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slidein_right_to_left, R.anim.fade_out,
                R.anim.fade_in, R.anim.slideout_left_to_right)
            .replace(R.id.mainFrame, FragSignUp()).addToBackStack(null).commit()
    }

    private fun backClickHandler() {
        parentFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .replace(R.id.mainFrame, FragHome()).commit()
    }

    private fun initial(view: View) {
        MainActivity.btmCard.visibility = View.GONE
        MainActivity.fab.visibility = View.GONE
        ////////////////////////////////////////
    }
}