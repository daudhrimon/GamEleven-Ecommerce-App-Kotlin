package com.daud.gamelevenecommerce.Helper

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.fragment.app.FragmentActivity
import com.daud.gamelevenecommerce.Model.UserModel

class DbHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Database Version
        private val DATABASE_VERSION = 1

        // Database Name
        private val DATABASE_NAME = "UserManager.db"

        // User table name
        private val USER_TABLE = "user"
        private val ADDRESS_TABLE = "address"

        // User Table Columns names
        private val USER_ID = "id"
        private val USER_FIRST_NAME = "firstName"
        private val USER_LAST_NAME = "lastName"
        private val USER_EMAIL = "email"
        private val USER_PASSWORD = "password"
        private val USER_PHONE = "phone"
        private val USER_BIRTH_DATE = "birthDate"
        private const val USER_GENDER = "gender"

        // Address Table Columns names
        private val USER_ADDRESS = "address"
        private val USER_AREA = "area"
        private val USER_CITY = "city"
        private val USER_REGION = "region"
        private val USER_COUNTRY = "country"
        private val USER_ZIP = "zip"
        private val USER_COMPANY = "company"
    }

    // create user table sql query
    private val CREATE_USER_TABLE = ("CREATE TABLE " + USER_TABLE + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_FIRST_NAME + " TEXT," + USER_LAST_NAME + " TEXT," + USER_EMAIL + " TEXT," + USER_PASSWORD + " TEXT,"
                + USER_PHONE + " TEXT," + USER_BIRTH_DATE + " TEXT," + USER_GENDER + " TEXT)")

    // create address table sql query
    private val CREATE_ADDRESS_TABLE = ("CREATE TABLE " + ADDRESS_TABLE + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_ADDRESS + " TEXT," + USER_AREA + " TEXT," + USER_CITY + " TEXT," + USER_REGION + " TEXT,"
                + USER_COUNTRY + " TEXT," + USER_ZIP + " TEXT," + USER_COMPANY + " TEXT)")

    //private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $USER_TABLE"

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(CREATE_USER_TABLE)

        db?.execSQL(CREATE_ADDRESS_TABLE)
    }

    // upgrade! upgrade! upgrade!, i don't like upgrade, so i Avoid
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        /*db?.execSQL(DROP_USER_TABLE)
        onCreate(db)*/
    }

    // insert UserData To USER_TABLE
    fun insertUserData (userTable: UserModel) {
        val db: SQLiteDatabase = writableDatabase
        val cv = ContentValues()
        cv.put(USER_FIRST_NAME, userTable.firstName)
        cv.put(USER_LAST_NAME, userTable.lastName)
        cv.put(USER_EMAIL, userTable.email)
        cv.put(USER_PASSWORD, userTable.password)
        cv.put(USER_PHONE, userTable.phone)
        cv.put(USER_BIRTH_DATE, userTable.birthDate)
        cv.put(USER_GENDER, userTable.gender)
        db.insert(USER_TABLE, null, cv)
        (context as FragmentActivity).supportFragmentManager.popBackStack()
    }

    // update Personal Info to USER_TABLE
    fun updatePersonalInfo (id: String,userTable: UserModel){
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put(USER_FIRST_NAME, userTable.firstName)
        cv.put(USER_LAST_NAME, userTable.lastName)
        cv.put(USER_PHONE, userTable.phone)
        cv.put(USER_BIRTH_DATE, userTable.birthDate)
        cv.put(USER_GENDER, userTable.gender)
        db.update(USER_TABLE, cv, USER_ID + " =?", arrayOf(id))
    }

    // update email to USER_TABLE
    fun updateEmail(id: String?, email: String?){
        val db:SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()
        cv.put(USER_EMAIL,email)
        db.update(USER_TABLE, cv, USER_ID + " =?", arrayOf(id))
    }

    fun checkEmailAndPass(email:String, password: String): Int {
        val db:SQLiteDatabase = this.readableDatabase
        val cursor: Cursor = db.rawQuery("select * from " + USER_TABLE + " where "
                + USER_EMAIL + " =? and " + USER_PASSWORD + " =?", arrayOf(email,password))

        while (cursor.moveToFirst()){
            @SuppressLint("Range") val id = cursor.getInt(cursor.getColumnIndex(USER_ID))
            return id
        }
        return -1
    }

    @SuppressLint("Range")
    fun getUserData(id: String?): UserModel?{
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from " + USER_TABLE + " where " + USER_ID + " =?", arrayOf(id))

        while (cursor.moveToFirst()){
            val userData = UserModel(cursor.getString(cursor.getColumnIndex(USER_FIRST_NAME)),
                    cursor.getString(cursor.getColumnIndex(USER_LAST_NAME)), cursor.getString(cursor.getColumnIndex(USER_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(USER_PASSWORD)), cursor.getString(cursor.getColumnIndex(USER_PHONE)),
                    cursor.getString(cursor.getColumnIndex(USER_BIRTH_DATE)), cursor.getString(cursor.getColumnIndex(USER_GENDER)))
            return userData
        }
        return null
    }
}