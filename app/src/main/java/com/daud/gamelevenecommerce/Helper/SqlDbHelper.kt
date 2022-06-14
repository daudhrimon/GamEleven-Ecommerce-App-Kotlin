package com.daud.gamelevenecommerce.Helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.SyncStateContract
import com.daud.gamelevenecommerce.Helper.SqlDbHelper.Companion.USER_ID
import com.daud.gamelevenecommerce.Model.UserTableModel

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

    // insert UserData To USER_TABLE
    fun insertUserData (userTable:UserTableModel) {
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put(USER_FIRST_NAME, userTable.first_name)
        cv.put(USER_LAST_NAME, userTable.last_name)
        cv.put(USER_EMAIL, userTable.email)
        cv.put(USER_PASSWORD, userTable.password)
        cv.put(USER_PHONE, userTable.phone)
        cv.put(USER_BIRTH_DATE, userTable.birth_date)
        cv.put(USER_GENDER, userTable.gender)
        db.insert(USER_TABLE, null, cv)
        db.close()
    }

    // update Personal Info to USER_TABLE
    fun updatePersonalInfo (userTable: UserTableModel){
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put(USER_FIRST_NAME, userTable.first_name)
        cv.put(USER_LAST_NAME, userTable.last_name)
        cv.put(USER_PHONE, userTable.phone)
        cv.put(USER_BIRTH_DATE, userTable.birth_date)
        cv.put(USER_GENDER, userTable.gender)
        db.update(USER_TABLE, cv, USER_ID + " =? ", arrayOf(userTable.id.toString()))
        db.close()
    }

    // update email to USER_TABLE
    fun updateEmail(id: Int, email: String){
        val db:SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put(USER_EMAIL,email)
        db.update(USER_TABLE, cv, USER_ID + " =? ", arrayOf(id.toString()))
        db.close()
    }

    fun checkEmailAndPass(email:String, password: String){

    }


}