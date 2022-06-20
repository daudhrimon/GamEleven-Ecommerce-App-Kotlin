package com.daud.gamelevenecommerce.Fragment

import android.app.Activity
import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Helper.DbHelper
import com.daud.gamelevenecommerce.Model.UserModel
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.SharedPref
import com.daud.gamelevenecommerce.Util.Util
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*
import android.content.Intent as Intent

class FragProfile : Fragment() {
    private lateinit var userData: UserModel
    private lateinit var pProImage: CircleImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.frag_profile, container, false)

        initial(view)

        // user info getting from database
        getUserData()

        // set user info
        setUserInfo(view)

        val backBtn = view.findViewById<ImageButton>(R.id.profileBack)
        backBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            backBtnClickHandler()
        })

        val pImagePen = view.findViewById<FloatingActionButton>(R.id.pImagePen)
        pImagePen.setOnClickListener(View.OnClickListener { view1: View? ->
            pImagePenClickHandler()
        })

        val personalEd = view.findViewById<TextView>(R.id.personalEd)
        personalEd.setOnClickListener(View.OnClickListener { view1: View? ->
            personalEdClickHandler()
        })

        val emailEd = view.findViewById<TextView>(R.id.emailEd)
        emailEd.setOnClickListener(View.OnClickListener { view1: View? ->
            emailEdClickHandler()
        })

        return view
    }

    private fun pImagePenClickHandler() {
        ImagePicker.with(this)
            .crop(1f, 1f)
            .compress(1024)         //Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080) //Final image resolution will be less than 1080 x 1080(Optional)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    // set user info
    private fun setUserInfo(view: View?) {
        val pNameTv = view?.findViewById<TextView>(R.id.pNameTv)
        val piNameTv = view?.findViewById<TextView>(R.id.piNameTv)

        if (userData.image.isNotEmpty()){
            pProImage.setImageURI(userData.image.toUri())
        }

        if (userData.firstName.isNotEmpty()) {
            pNameTv?.text = userData.firstName + " " + userData.lastName
            piNameTv?.text = userData.firstName + " " + userData.lastName
        }

        val piContact = view?.findViewById<TextView>(R.id.piContact)
        if (userData.phone.isNotEmpty()){
            piContact?.text = userData.phone
        }

        val piGender = view?.findViewById<TextView>(R.id.piGender)
        if (userData.gender.isNotEmpty()){
            piGender?.text = userData.gender
        }

        val piBirthDate = view?.findViewById<TextView>(R.id.piBirthDate)
        if (userData.birthDate.isNotEmpty()){
            piBirthDate?.text = userData.birthDate
        }

        val piEmail = view?.findViewById<TextView>(R.id.piEmail)
        if (userData.email.isNotEmpty()){
            piEmail?.text = userData.email
        }
    }

    // get user data from database
    private fun getUserData() {
        val dbHelper = DbHelper(requireContext())
        val sharedPref = SharedPref()
        sharedPref.init(requireContext())
        userData = dbHelper?.getUserData(sharedPref.ID())!!
    }

    private fun emailEdClickHandler() {
        val btmSheet: BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.AppBottomSheetDialogTheme)
        val sheetView = LayoutInflater.from(context).inflate(R.layout.btmsheet_email,null)
        btmSheet.setContentView(sheetView)
        val editEmail = sheetView.findViewById<EditText>(R.id.editEmail)
        val saveEmailBtn = sheetView.findViewById<AppCompatButton>(R.id.saveEmailBtn)
        btmSheet.show()

        sheetView.setOnClickListener(View.OnClickListener { view1: View? ->
            Util.hideSoftKeyBoard(requireContext(),sheetView)
        })

        if (userData.email.isNotEmpty()){
            editEmail.setText(userData.email)
        }


        saveEmailBtn.setOnClickListener(View.OnClickListener { view1: View? ->
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editEmail?.text.toString()).matches()){
                editEmail.error = "Empty"
                editEmail.requestFocus()
                return@OnClickListener
            }

            if (editEmail.text.toString() == userData.email){
                Toast.makeText(requireContext(),"You changed nothing yet",Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            //updating email to database
            val dbHelper = DbHelper(requireContext())
            val sharedPref = SharedPref()
            sharedPref.init(requireContext())
            dbHelper.updateEmail(sharedPref.ID(),editEmail.text.toString().trim())

            btmSheet.dismiss()

            getUserData()

            setUserInfo(requireView())
        })
    }

    private fun personalEdClickHandler() {
        val btmSheet: BottomSheetDialog = context?.let { BottomSheetDialog(it, R.style.AppBottomSheetDialogTheme) }!!
        val sheetView = LayoutInflater.from(context).inflate(R.layout.btmsheet_personalinfo,null)
        btmSheet.setContentView(sheetView)
        val editFName = sheetView.findViewById<EditText>(R.id.editFName)
        val editLName = sheetView.findViewById<EditText>(R.id.editLName)
        val editContact = sheetView.findViewById<EditText>(R.id.editContact)
        val editBirthDate = sheetView.findViewById<EditText>(R.id.editBirthDate)
        val genderRadio = sheetView.findViewById<RadioGroup>(R.id.editGender)
        val editSaveBtn = sheetView.findViewById<AppCompatButton>(R.id.editSaveBtn)
        // initial value of gender
        var gender: String = userData.gender

        btmSheet.show()

        // set Old value
        editFName.setText(userData.firstName)
        editLName.setText(userData.lastName)
        editContact.setText(userData.phone)

        // set user old birthdate
        if (userData.birthDate.isNotEmpty()){
            editBirthDate.setText(userData.birthDate)
        }

        // set user old Gender
        if (userData.gender.isNotEmpty()){
            val radioId = getGenderRadioId(userData.gender)
            if (radioId != -1){
                genderRadio.check(radioId)
            }
        }

        // bottom sheet touch to hide KEYBOARD
        sheetView.setOnClickListener(View.OnClickListener { view: View? ->
            Util.hideSoftKeyBoard(requireContext(),sheetView)
        })

        // genderRadio click Handler
        genderRadio.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            genderRadio.setBackgroundColor(Color.parseColor("#00000000"))
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
                genderRadio.setBackgroundResource(R.drawable.selector_mycart)
                Toast.makeText(context,"Gender Unselected",Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            // checking user changes data or not
            if (editFName?.text.toString() == userData.firstName && editLName?.text.toString() == userData.lastName &&
                editContact?.text.toString() == userData.phone && editBirthDate?.text.toString() == userData.birthDate && gender == userData.gender){
                Toast.makeText(requireContext(),"You changed nothing yet",Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            val dbHelper = DbHelper(requireContext())
            val sharedPref = SharedPref()
            sharedPref.init(requireContext())
            dbHelper.updateUserData(sharedPref.ID(), editFName?.text.toString(), editLName?.text.toString(),
                editContact?.text.toString(), editBirthDate?.text.toString(),gender)

            btmSheet.dismiss()

            getUserData()

            setUserInfo(requireView())
        })
    }

    private fun getGenderRadioId(gender: String): Int {
        when(gender){
            "Male"-> return R.id.gnMale
            "Female"-> return R.id.gnFemale
            "Other"-> return R.id.gnOther
        }
        return -1
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

    // for getting image from camera or gallery
    private val startForProfileImageResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        val resultCode = result.resultCode
        val data = result.data

        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val imageUri = data?.data!!
            //this method will insert IMAGE to databse
            insertImageToDatabse(imageUri)
            // set image to ImageView
            pProImage.setImageURI(imageUri)

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun insertImageToDatabse(imageUri: Uri) {
        val dbHelper = DbHelper(requireContext())
        val sharedPref = SharedPref()
        sharedPref.init(requireContext())
        dbHelper.insertImage(sharedPref.ID(),imageUri.toString())
    }

    private fun initial(view: View?) {
        MainActivity.fab.visibility = View.GONE
        MainActivity.btmCard.visibility = View.GONE
        pProImage = view?.findViewById(R.id.pProImage)!!
    }
}