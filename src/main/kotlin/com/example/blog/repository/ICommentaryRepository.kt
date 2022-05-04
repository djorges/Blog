package com.example.blog.repository

import com.example.blog.entity.CommentaryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ICommentaryRepository :JpaRepository<CommentaryEntity, Int>{
    fun findByPostId(postId:Int):List<CommentaryEntity>
}