package com.example.blog.entity

import com.example.blog.model.Post
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "posts", uniqueConstraints = [UniqueConstraint(columnNames = ["title"])])
class PostEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Int? = null,

    @Column(name = "title", nullable = false)
    val title:String,

    @Column(name = "description", nullable = false)
    val description:String,

    @Column(name = "content", nullable = false)
    val content:String,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val commentaries:List<CommentaryEntity> = emptyList()
) {
    fun toPostModel(): Post {
        return Post(
            id = id,
            title = title,
            description = description,
            content = content
        )
    }
}