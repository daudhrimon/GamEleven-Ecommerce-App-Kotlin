package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.R

class FragSignUp : Fragment() {
    private lateinit var backBtn: ImageButton
    private lateinit var signInBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_sign_up, container, false)

        initial(view)

        backBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            backBtnClickHandler()
        })

        signInBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            backBtnClickHandler()
        })

        return view
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
    }
}