package com.daud.gamelevenecomme.Fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Fragment.FragHome
import com.daud.gamelevenecommerce.Fragment.FragOrderList
import com.daud.gamelevenecommerce.Fragment.FragProfile
import com.daud.gamelevenecommerce.Fragment.FragSignIn
import com.daud.gamelevenecommerce.Helper.DbHelper
import com.daud.gamelevenecommerce.Model.AddressModel
import com.daud.gamelevenecommerce.Model.UserModel
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.SharedPref
import com.daud.gamelevenecommerce.Util.Util
import com.google.android.material.bottomsheet.BottomSheetDialog
import de.hdodenhof.circleimageview.CircleImageView

class FragAccount : Fragment() {
    private lateinit var userData: UserModel
    private lateinit var address: AddressModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.frag_account, container, false)

        initial()

        // retrieve data from database
        getData()

        // set data to user layout
        setUserData(view)

        getAddress()

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
            addressBtmSheet()
        })

        val cngLanLay: RelativeLayout = view.findViewById(R.id.cngLanLay)
        cngLanLay.setOnClickListener(View.OnClickListener { view1: View? ->
            cngLanLayClickHandler()
        })

        val logoutLay: RelativeLayout = view.findViewById(R.id.logoutLay)
        logoutLay.setOnClickListener(View.OnClickListener { view1: View? ->
            logoutLayClickHandler()
        })

        return view
    }

    // retrieve address Data to AddressModel from database
    private fun getAddress() {
        val dbHelper = DbHelper(requireContext())
        val sharedPref = SharedPref()
        sharedPref.init(requireContext())
        address = dbHelper.getAddress(sharedPref.ID())!!
    }

    // set data to user layout
    private fun setUserData(view: View) {
        val acProImage = view.findViewById<CircleImageView>(R.id.acProImage)
        if (userData.image.isNotEmpty()){
            ////////////////////////////////////////////////////////////////////////////////////////
        }

        val userName = view.findViewById<TextView>(R.id.userName)
        if (userData.firstName.isNotEmpty()){
            userName.text = userData.firstName + " " + userData.lastName
        }

        val userPhone = view.findViewById<TextView>(R.id.userPhone)
        if (userData.phone.isNotEmpty()){
            userPhone.text = userData.phone
        }
    }

    // retrieve data from database
    private fun getData() {
        val dbHelper = context?.let { DbHelper(it) }
        val sharedPref = SharedPref()
        context?.let { sharedPref.init(it) }
        userData = dbHelper?.getUserData(sharedPref.ID())!!
    }

    private fun logoutLayClickHandler() {
        val sharedPref = SharedPref()
        context?.let { sharedPref.init(it) }
        sharedPref.write("SIGNIN","")
        sharedPref.write("ID","")
        parentFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragSignIn()).commit()
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

    private fun addressBtmSheet() {
        val btmSheet = context?.let { BottomSheetDialog(it, R.style.AppBottomSheetDialogTheme) }
        btmSheet?.setContentView(R.layout.btmsheet_no_address)
        val backBtn = btmSheet?.findViewById<ImageButton>(R.id.addressBack)
        val plusBtn = btmSheet?.findViewById<ImageButton>(R.id.addressPlus)
        val noAddressTv = btmSheet?.findViewById<TextView>(R.id.noAddressTv)
        val addressLay = btmSheet?.findViewById<LinearLayout>(R.id.addressLay)
        btmSheet?.show()

        // address show to address BottomSheet
        if (address.address.isNotEmpty()){
            plusBtn?.setImageResource(R.drawable.pencil)
            noAddressTv?.visibility = View.GONE
            addressLay?.visibility = View.VISIBLE

            val addressTv = btmSheet?.findViewById<TextView>(R.id.addressTv)?.setText(address.address)
            val areaTv = btmSheet?.findViewById<TextView>(R.id.areaTv)?.setText(address.area)
            val zipTv = btmSheet?.findViewById<TextView>(R.id.zipTv)?.setText(address.zip)
            val cityTv = btmSheet?.findViewById<TextView>(R.id.cityTv)?.setText(address.city)
            val countryTv = btmSheet?.findViewById<TextView>(R.id.countryTv)?.setText(address.country)
            val regionTv = btmSheet?.findViewById<TextView>(R.id.regionTv)?.setText(address.region)
        }

        // back button Handler
        backBtn!!.setOnClickListener { view: View? -> btmSheet.dismiss() }

        // PLUS or EDIT button Handler
        plusBtn!!.setOnClickListener { view: View? ->
            addressAddingBtmSheet()
            btmSheet.dismiss()
        }
    }

    // address add or Edit BottomSheet
    private fun addressAddingBtmSheet() {
        val btmDialog = context?.let { BottomSheetDialog(it, R.style.AppBottomSheetDialogTheme) }
        val sheetView: View = LayoutInflater.from(context).inflate(R.layout.btmsheet_address_half,null, false)

        val hAddressEt = sheetView.findViewById<EditText>(R.id.hAddressEt)
        val hAreaEt = sheetView.findViewById<EditText>(R.id.hAreaEt)
        val hCityEt = sheetView.findViewById<EditText>(R.id.hCityEt)
        val hRegionEt = sheetView.findViewById<EditText>(R.id.hRegionEt)
        val hCountryEt = sheetView.findViewById<EditText>(R.id.hCountryEt)
        val hZipEt = sheetView.findViewById<EditText>(R.id.hZipEt)
        val hCompanyEt = sheetView.findViewById<EditText>(R.id.hCompanyEt)

        val hSaveBtn = sheetView.findViewById<AppCompatButton>(R.id.hSaveBtn)
        btmDialog?.setContentView(sheetView)
        btmDialog?.show()

        // this one for hide keyboard when touch on bottomSheet
        sheetView.setOnClickListener(View.OnClickListener { view1: View? ->
            Util.hideSoftKeyBoard(requireContext(),sheetView)
        })

        // if ADDRESS is avaiable then here will show
        if (address.address.isNotEmpty()){
            hAddressEt.setText(address.address)
        }

        if (address.area.isNotEmpty()){
            hAreaEt.setText(address.address)
        }

        if (address.city.isNotEmpty()){
            hCityEt.setText(address.area)
        }

        if (address.region.isNotEmpty()){
            hRegionEt.setText(address.region)
        }

        if (address.country.isNotEmpty()){
            hCountryEt.setText(address.country)
        }

        if (address.zip.isNotEmpty()){
            hZipEt.setText(address.zip)
        }

        if (address.company.isNotEmpty()){
            hCompanyEt.setText(address.company)
        }

        //ADDRESS Save Button Click handler
        hSaveBtn.setOnClickListener(View.OnClickListener { view1: View? ->

            saveBtnClickHander(btmDialog, hAddressEt, hAreaEt, hCityEt, hRegionEt, hCountryEt, hZipEt, hCompanyEt)

        })
    }

    //ADDRESS Save Button Click handler
    private fun saveBtnClickHander(btmDialog: BottomSheetDialog?, hAddressEt: EditText, hAreaEt: EditText,
                                   hCityEt: EditText, hRegionEt: EditText, hCountryEt: EditText, hZipEt: EditText, hCompanyEt: EditText
    ) {
        // checking not empty or not
        if (hAddressEt.text.toString().isEmpty()){
            hAddressEt.setError("Empty")
            hAddressEt.requestFocus()
            return
        }
        if (hAreaEt.text.toString().isEmpty()){
            hAreaEt.setError("Empty")
            hAreaEt.requestFocus()
            return
        }
        if (hCityEt.text.toString().isEmpty()){
            hCityEt.setError("Empty")
            hCityEt.requestFocus()
            return
        }
        if (hRegionEt.text.toString().isEmpty()){
            hRegionEt.setError("Empty")
            hRegionEt.requestFocus()
            return
        }
        if (hCountryEt.text.toString().isEmpty()){
            hCountryEt.setError("Empty")
            hCountryEt.requestFocus()
            return
        }
        if (hZipEt.text.toString().isEmpty()){
            hZipEt.setError("Empty")
            hZipEt.requestFocus()
            return
        }
        if (hCompanyEt.text.toString().isEmpty()){
            hCompanyEt.setError("Empty")
            hCompanyEt.requestFocus()
            return
        }

        val dbHelper = DbHelper(requireContext())
        val sharedPref = SharedPref()
        sharedPref.init(requireContext())

        // if address not avaiable in database then data will INSERT to database
        if (address.address.isEmpty()){
            dbHelper.insertAddress(sharedPref.ID(), AddressModel(hAddressEt.text.toString(), hAreaEt.text.toString(), hCityEt.text.toString(),
                hRegionEt.text.toString(), hCountryEt.text.toString(),hZipEt.text.toString(),hCompanyEt.text.toString()))

            btmDialog?.dismiss()

            // if address avaiable in database then data will UPDATE to database
        } else {

            //checking user changes anything or not....cz i don't want to update databse with same data as old
            if (hAddressEt.text.toString() == address.address
                && hAreaEt.text.toString() == address.area
                && hCityEt.text.toString() == address.city
                &&  hRegionEt.text.toString() == address.region
                && hCountryEt.text.toString() == address.country
                && hZipEt.text.toString() == address.zip
                && hCompanyEt.text.toString() == address.company){

                Toast.makeText(requireContext(),"You changed nothing yet",Toast.LENGTH_SHORT).show()

                // if data is not matches with old then it will be push to Database for UPDATE address
            } else {

                dbHelper.updateAddress(sharedPref.ID(), AddressModel(hAddressEt.text.toString(), hAreaEt.text.toString(), hCityEt.text.toString(),
                    hRegionEt.text.toString(), hCountryEt.text.toString(),hZipEt.text.toString(),hCompanyEt.text.toString()))

                btmDialog?.dismiss()
            }
        }
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