package com.example.blog.model

import com.example.blog.entity.PostEntity
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size


data class Post(
    val id:Int? = null,

    @NotEmpty
    @Size(min = 4, message = "The title must have at least 4 characters")
    val title:String,

    @NotEmpty
    @Size(min = 10, message = "The description must have at least 10 characters")
    val description:String,

    @NotEmpty
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