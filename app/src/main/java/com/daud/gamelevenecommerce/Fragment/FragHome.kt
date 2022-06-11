package com.daud.gamelevenecommerce.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import androidx.recyclerview.widget.RecyclerView
import com.daud.gamelevenecommerce.Activity.MainActivity
import com.daud.gamelevenecommerce.Adapter.*
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.Data
import com.denzcoskun.imageslider.ImageSlider

class FragHome : Fragment() {
    private val data = Data()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.frag_home, container, false)

        initial(view)

        setDemoData(view)

        val menuBtn: ImageButton = view.findViewById(R.id.menuBtn)
        menuBtn.setOnClickListener(View.OnClickListener { view: View? ->
            menuBtnClickHandler()
        })

        val couponBtn: ImageView = view.findViewById(R.id.couponBtn)
        couponBtn.setOnClickListener(View.OnClickListener { view: View? ->
            clickHandlerDemo()
        })

        val giftCBtn: ImageView = view.findViewById(R.id.giftCBtn)
        giftCBtn.setOnClickListener(View.OnClickListener { view: View? ->
            clickHandlerDemo()
        })

        val slashBtn: ImageView = view.findViewById(R.id.slashBtn)
        slashBtn.setOnClickListener(View.OnClickListener { view: View? ->
            clickHandlerDemo()
        })

        val pcbBtn: ImageView = view.findViewById(R.id.pcbBtn)
        pcbBtn.setOnClickListener(View.OnClickListener { view: View? ->
            clickHandlerDemo()
        })

        return view
    }

    private fun setDemoData(view: View) {
        textButtonDemo(view)

        imageSliderDemo(view)

        flashDealsDemo(view)

        dailyFeaturesDemo(view)

        hotCategoryDemo(view)

        brandsDemo(view)

        bestSaleDemo(view)
    }

    private fun clickHandlerDemo() {
        parentFragmentManager.beginTransaction().setTransition(TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame,FragSearchFilter()).addToBackStack(null).commit()
    }

    private fun bestSaleDemo(view: View) {
        val bestSLay: LinearLayout = view.findViewById(R.id.bestSLay)
        bestSLay.visibility = View.VISIBLE
        val bestSRecycler: RecyclerView = view.findViewById(R.id.bestSRecycler)
        bestSRecycler.adapter = data.getAds()?.let { context?.let { it1 -> AdsAdapter(it1, it) } }
    }

    private fun brandsDemo(view: View) {
        val brandsLay: LinearLayout = view.findViewById(R.id.brandsLay);
        brandsLay.visibility = View.VISIBLE
        val brandsRecycler: RecyclerView = view.findViewById(R.id.brandsRecycler)
        brandsRecycler.adapter = data.getBrand()?.let { context?.let { it1 -> BrandsAdapter(it1, it) } }
    }

    private fun hotCategoryDemo(view: View) {
        val hotCLay: LinearLayout = view.findViewById(R.id.hotCLay)
        hotCLay.visibility = View.VISIBLE
        val hotCRecycler: RecyclerView = view.findViewById(R.id.hotCRecycler)
        hotCRecycler.adapter = data.getHotC()?.let { context?.let { it1 -> HotCategoryAdapter(it1, it) } }
    }

    private fun dailyFeaturesDemo(view: View) {
        val dailyFLay: LinearLayout = view.findViewById(R.id.dailyFLay)
        dailyFLay.visibility = View.VISIBLE
        val dailyFRecycler: RecyclerView = view.findViewById(R.id.dailyFRecycler)
        dailyFRecycler.adapter = data.getDailyF()?.let { context?.let { it1 -> DailyFeatureAdapter(it1, it) } }
    }


    private fun flashDealsDemo(view: View) {
        val flashDLay: LinearLayout = view.findViewById(R.id.flashDLay)
        flashDLay.visibility = View.VISIBLE
        val flashDRecycler: RecyclerView = view.findViewById(R.id.flashDRecycler)
        flashDRecycler.adapter = data.getFlashD()?.let { context?.let { it1 -> FlashDealsAdapter(it1, it) } }
    }

    private fun textButtonDemo(view: View) {
        val textRecycler: RecyclerView = view.findViewById(R.id.textRecycler)
        textRecycler.adapter = context?.let { TextAdapter(it,data.getText()) }
    }

    private fun imageSliderDemo(view: View) {
        val imageSlider: ImageSlider = view.findViewById(R.id.imageSlider)
        data.homeSlider()?.let { imageSlider.setImageList(it) }
    }

    private fun menuBtnClickHandler() {
        parentFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragCategory()).commit()
        MainActivity.btmNav.selectedItemId = R.id.category
    }

    private fun initial(view: View) {
        MainActivity.btmCard.visibility = View.VISIBLE
        MainActivity.fab.visibility = View.VISIBLE
        MainActivity.btmNav.selectedItemId = R.id.home
    }
}