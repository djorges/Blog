package com.example.blog.model

import com.example.blog.entity.UserEntity


class User (
    val id:Long? = null,

    val username:String,

    val email:String,

    val password:String
){
    fun toEntity():UserEntity{
        return UserEntity(
            id = id,
            username = username,
            email = email,
            password = password
        )
    }
}