package com.daud.gamelevenecommerce.Util

import android.content.Context
import android.content.SharedPreferences

class SharedPref() {
    private var mSharedPref: SharedPreferences? = null
    val NAME = "com.bdtask.sebaghar_nurse"

    private fun SharedPref() {}

    fun init(context: Context) {
        if (mSharedPref == null) mSharedPref =
            context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }

    fun read(key: String?, defValue: String?): String? {
        return mSharedPref!!.getString(key, defValue)
    }

    fun WELCOME(): String? {
        return mSharedPref!!.getString("WELCOME", "")
    }

    fun SIGNIN(): String? {
        return mSharedPref!!.getString("SIGNIN", "") //OK
    }

    fun write(key: String?, value: String?) {
        val prefsEditor = mSharedPref!!.edit()
        prefsEditor.putString(key, value)
        prefsEditor.commit()
    }
}