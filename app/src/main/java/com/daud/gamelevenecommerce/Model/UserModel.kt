package com.daud.gamelevenecommerce.Model

data class UserModel(
    val id:Int,
    val firstName:String,
    val lastName:String,
    val email:String,
    val password:String,
    val phone:String,
    val birthDate:String,
    val gender:String
)