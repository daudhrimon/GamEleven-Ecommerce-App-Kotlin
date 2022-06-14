package com.daud.gamelevenecommerce.Sql

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DbHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

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

    // create table sql query
    private val CREATE_USER_TABLE = ("CREATE TABLE " + USER_TABLE + "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_FIRST_NAME +
            " TEXT," + USER_LAST_NAME + " TEXT,"+ USER_EMAIL + " TEXT," + USER_PASSWORD + " TEXT" + USER_PHONE + " TEXT" + ")")

    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}