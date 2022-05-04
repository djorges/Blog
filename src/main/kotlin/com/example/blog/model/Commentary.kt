package com.example.blog.model

import com.example.blog.entity.CommentaryEntity
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class Commentary(
    val id: Int?=null,

    @NotEmpty(message = "The title must not be empty")
    val title:String,

    @NotEmpty(message = "The email must not be empty")
    @Email
    val email:String,

    @NotEmpty
    @Size(min = 10, message = "The commentary must have at least 10 characters")
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
