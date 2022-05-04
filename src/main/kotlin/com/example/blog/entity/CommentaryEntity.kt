package com.example.blog.entity

import com.example.blog.model.Commentary
import javax.persistence.*

@Entity
@Table(name = "comments")
class CommentaryEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?=null,
    var title:String,
    var email:String,
    var content:String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    var post:PostEntity?=null
){
    fun toModel():Commentary{
        return Commentary(
            id = id,
            title = title,
            email = email,
            content = content
        )
    }
}