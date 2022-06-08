package com.daud.gamelevenecommerce.Activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentTransaction
import com.daud.gamelevenecommerce.Fragment.*
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.SharedPref
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var btmNav: BottomNavigationView
        lateinit var fab: FloatingActionButton
        lateinit var btmCard: CardView
    }
    val sharedPref = SharedPref()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initial()

        fab.setOnClickListener(View.OnClickListener { view ->
            if (btmNav.getSelectedItemId() != R.id.home) {
                fabClickHandler()
            }
        })

        btmNav.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            if (item.itemId != btmNav.getSelectedItemId()) {
                btmNavItemSelectHandler(item)
            }
            true
        })
    }

    private fun fabClickHandler() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .replace(R.id.mainFrame, FragHome()).commit()
    }

    private fun btmNavItemSelectHandler(item: MenuItem) {
        when (item.itemId) {
            R.id.category -> supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.mainFrame, FragCategory()).commit()
            R.id.wishlist -> supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.mainFrame, FragWishlist()).commit()
            R.id.cart -> supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.mainFrame, FragCart()).commit()
            R.id.account ->
                if (sharedPref.SIGNIN()=="OK") {
                supportFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.mainFrame, FragAccount()).commit()
            } else {
                supportFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.mainFrame, FragSignIn()).commit()
            }
            else -> {}
        }
    }


    private fun initial() {
        btmNav = findViewById(R.id.btmNav)
        fab = findViewById(R.id.fab)
        btmCard = findViewById(R.id.btmCard)
        sharedPref.init(this)
        btmNav.getMenu().getItem(2).setEnabled(false)
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.mainFrame, FragHome()).commit()
        btmNav.selectedItemId = R.id.home
    }
}