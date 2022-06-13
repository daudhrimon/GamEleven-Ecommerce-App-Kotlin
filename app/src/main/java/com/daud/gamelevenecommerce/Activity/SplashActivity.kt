package com.daud.gamelevenecommerce.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.daud.gamelevenecommerce.Fragment.FragOnBoarding
import com.daud.gamelevenecommerce.R
import com.daud.gamelevenecommerce.Util.SharedPref

class SplashActivity : AppCompatActivity() {
    private lateinit var spImg: ImageView
    private lateinit var spFrame: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initial()

        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPref = SharedPref()
            sharedPref.init(this)
            if (sharedPref.WELCOME()=="DONE") {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            } else {
                val fadeoutAnim = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.fade_out_long)
                spImg.startAnimation(fadeoutAnim)
                spFrame.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out_long)
                    .replace(R.id.spFrame, FragOnBoarding()).commit()
                spImg.visibility = View.GONE
            }
        }, 1000)
    }

    private fun initial() {
        spImg = findViewById(R.id.spImg)
        spFrame = findViewById(R.id.spFrame)
    }
}