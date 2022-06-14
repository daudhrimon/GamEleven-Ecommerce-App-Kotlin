package com.daud.gamelevenecommerce.Fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.Fragment
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Helper.DbHelper
import com.daud.gamelevenecommerce.Model.UserTableModel
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.Util

class FragSignUp : Fragment() {
    private lateinit var backBtn: ImageButton
    private lateinit var signInBtn: Button
    private lateinit var createAcBtn: Button
    private lateinit var agreeBox: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_sign_up, container, false)

        initial(view)

        backBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            context?.let { Util.hideSoftKeyBoard(it,view) }
            backBtnClickHandler()
        })

        signInBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            backBtnClickHandler()
        })

        createAcBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            context?.let { Util.hideSoftKeyBoard(it,view) }
            createAcBtnClickhandler(view)
        })

        agreeBox.setOnClickListener(View.OnClickListener { view1: View? ->
            agreeBoxClickHandler()
        })

        return view
    }

    private fun agreeBoxClickHandler() {
        agreeBox.setBackgroundColor(Color.parseColor("#00000000"))
    }

    private fun createAcBtnClickhandler(view: View?) {
        val dbHelper = context?.let { DbHelper(it) }
        val fNameEt = view?.findViewById<EditText>(R.id.fNameEt)
        val lNameEt = view?.findViewById<EditText>(R.id.lNameEt)
        val emailEt = view?.findViewById<EditText>(R.id.emailEt)
        val passwordEt = view?.findViewById<EditText>(R.id.passwordEt)
        val phoneEt = view?.findViewById<EditText>(R.id.phoneEt)


        if (fNameEt?.text.toString().isEmpty()){
            fNameEt?.error="Empty"
            fNameEt?.requestFocus()
            return
        }

        if (lNameEt?.text.toString().isEmpty()){
            lNameEt?.error="Empty"
            lNameEt?.requestFocus()
            return
        }

        if (emailEt?.text.toString().isEmpty()){
            emailEt?.error="Empty"
            emailEt?.requestFocus()
            return
        }

        if (passwordEt?.text.toString().isEmpty()){
            passwordEt?.error="Empty"
            passwordEt?.requestFocus()
            return
        }

        if (phoneEt?.text.toString().isEmpty()){
            phoneEt?.error="Empty"
            phoneEt?.requestFocus()
            return
        }

        if (agreeBox.isChecked == false){
            agreeBox.setBackgroundColor(Color.parseColor("#D81D4C"))
            return
        }

        val userModel = UserTableModel(-1, fNameEt?.text.toString().trim(), lNameEt?.text.toString().trim(),
            emailEt?.text.toString().trim(), passwordEt?.text.toString().trim(), phoneEt?.text.toString().trim(),"","")


        context?.let { dbHelper?.insertUserData(userModel, it) }
    }

    private fun backBtnClickHandler() {
        parentFragmentManager.popBackStack()
    }

    private fun initial(view: View) {
        MainActivity.btmCard.visibility = View.GONE
        MainActivity.fab.visibility = View.GONE
        ////////////////////////////////////////
        backBtn = view.findViewById(R.id.sUpBack)
        signInBtn = view.findViewById(R.id.upSignInBtn)
        createAcBtn = view.findViewById(R.id.createAcBtn)
        agreeBox = view.findViewById(R.id.agreeBox)
    }
}