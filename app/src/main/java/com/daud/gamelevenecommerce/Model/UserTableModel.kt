package com.daud.gamelevenecommerce.Model

data class UserTableModel(
    val id:Int,
    val first_name:String,
    val last_name:String,
    val email:String,
    val password:String,
    val phone:String,
    val birth_date:String,
    val gender:String
)