package com.daud.gamelevenecommerce.Fragment

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Helper.DbHelper
import com.daud.gamelevenecommerce.Model.UserModel
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.SharedPref
import com.daud.gamelevenecommerce.Util.Util
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


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
        val sheetView = LayoutInflater.from(context).inflate(R.layout.btmsheet_personalinfo,null)
        btmSheet.setContentView(sheetView)
        val editFName = sheetView.findViewById<EditText>(R.id.editFName)
        editFName.setText(userData.firstName)
        val editLName = sheetView.findViewById<EditText>(R.id.editLName)
        editLName.setText(userData.lastName)
        val editContact = sheetView.findViewById<EditText>(R.id.editContact)
        editContact.setText(userData.phone)
        val editBirthDate = sheetView.findViewById<EditText>(R.id.editBirthDate)
        val editGender = sheetView.findViewById<RadioGroup>(R.id.editGender)
        val editSaveBtn = sheetView.findViewById<AppCompatButton>(R.id.editSaveBtn)
        var gender: String = ""
        btmSheet.show()

        sheetView.setOnClickListener(View.OnClickListener { view: View? -> Util.hideSoftKeyBoard(requireContext(),sheetView) })

        editGender.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            editGender.setBackgroundColor(Color.parseColor("#00000000"))
            gender = getGender(checkedId)
        })

        editBirthDate.setOnClickListener(View.OnClickListener { view1 ->
            datePickerDialog(editBirthDate)
        })

        editSaveBtn?.setOnClickListener(View.OnClickListener { view1: View? ->
            if (editFName?.text.toString().isEmpty()){
                editFName?.error = "Empty"
                editFName?.requestFocus()
                return@OnClickListener
            }
            if (editLName?.text.toString().isEmpty()){
                editLName?.error = "Empty"
                editLName?.requestFocus()
                return@OnClickListener
            }
            if (editContact?.text.toString().isEmpty()){
                editContact?.error = "Empty"
                editContact?.requestFocus()
                return@OnClickListener
            }
            if (editBirthDate?.text.toString().isEmpty()){
                datePickerDialog(editBirthDate)
                return@OnClickListener
            }
            if (gender.isEmpty()){
                editGender.setBackgroundResource(R.drawable.selector_mycart)
                Toast.makeText(context,"Gender Unselected",Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

        })
    }

    private fun getGender(checkedId: Int): String {
        when(checkedId){
            R.id.gnMale-> return "Male"
            R.id.gnFemale-> return "Female"
            R.id.gnOther-> return "Other"
        }
        return ""
    }

    private fun datePickerDialog(editBirthDate: EditText) {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = context?.let {
            DatePickerDialog(
                    it, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    { datePicker, year, month, day ->
                        var month = month
                        month = month + 1
                        val Date = "$day / $month / $year"
                        editBirthDate.setText(Date)
                    }, year, month, day)
            }
        datePicker?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        datePicker.setTitle("Select Date")
        datePicker.setCancelable(false)
        datePicker.show()
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