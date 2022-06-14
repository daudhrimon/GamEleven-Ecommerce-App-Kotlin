package com.daud.gamelevenecommerce.Helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Database Version
        private val DATABASE_VERSION = 1

        // Database Name
        private val DATABASE_NAME = "UserManager.db"

        // User table name
        private val USER_TABLE = "user_table"
        private val ADDRESS_TABLE = "address_table"

        // User Table Columns names
        private val USER_ID = "id"
        private val USER_FIRST_NAME = "first_name"
        private val USER_LAST_NAME = "last_name"
        private val USER_EMAIL = "email"
        private val USER_PASSWORD = "password"
        private val USER_PHONE = "phone"
        private val USER_BIRTH_DATE = "birth_date"
        private val USER_GENDER = "gender"

        // Address Table Columns names
        private val USER_ADDRESS = "address"
        private val USER_AREA = "address"
        private val USER_CITY = "address"
        private val USER_REGION = "region"
        private val USER_COUNTRY = "country"
        private val USER_ZIP = "zip"
        private val USER_COMPANY = "company"
    }

    // create user table sql query
    private val CREATE_USER_TABLE =
        ("CREATE TABLE " + USER_TABLE + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_FIRST_NAME + " TEXT," + USER_LAST_NAME + " TEXT," + USER_EMAIL + " TEXT," + USER_PASSWORD + " TEXT"
                + USER_PHONE + " TEXT" + USER_BIRTH_DATE + " TEXT" + USER_GENDER + " TEXT" + ")")

    // create address table sql query
    private val CREATE_ADDRESS_TABLE =
        ("CREATE TABLE " + ADDRESS_TABLE + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_ADDRESS + " TEXT," + USER_AREA + " TEXT," + USER_CITY + " TEXT," + USER_REGION + " TEXT"
                + USER_COUNTRY + " TEXT" + USER_ZIP + " TEXT" + USER_COMPANY + " TEXT" + ")")

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(CREATE_USER_TABLE)
        }
        if (db != null) {
            db.execSQL(CREATE_ADDRESS_TABLE)
        }
    }

    // upgrade! upgrade! upgrade!, i don't like upgrade, so i Avoid
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    // insert UserData To Database
    fun insertUserData(first_name: String, last_name: String, email: String, password: String, phone: String) {
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put(USER_FIRST_NAME, first_name)
        cv.put(USER_LAST_NAME, last_name)
        cv.put(USER_EMAIL, email)
        cv.put(USER_PASSWORD, password)
        cv.put(USER_PHONE, phone)
        cv.put(USER_BIRTH_DATE, "")
        cv.put(USER_GENDER, "")
        db.insert(USER_TABLE, null, cv)
        db.close()
    }


}