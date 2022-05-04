package com.example.blog.model

import com.example.blog.entity.PostEntity


data class Post(
    val id:Int? = null,

    val title:String,

    val description:String,

    val content:String
) {
    fun toPostEntity(): PostEntity {
        return PostEntity(
            id=id,
            title = title,
            description = description,
            content = content
        )
    }
}