package com.example.blog.model

import com.example.blog.entity.CommentaryEntity

data class Commentary(
    val id: Int?=null,
    val title:String,
    val email:String,
    val content:String,
){
    fun toEntity():CommentaryEntity{
        return CommentaryEntity(
            id = id,
            title = title,
            email = email,
            content = content
        )
    }
}
