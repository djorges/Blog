package com.example.blog.service

import com.example.blog.model.Post
import org.springframework.data.domain.Page

interface IPostService {
    fun create(post: Post): Post
    fun getAll(page: Int, pageSize: Int, sortBy:String, sortDir:String):Page<Post>
    fun getById(id: Int): Post
    fun update(post: Post): Post
    fun delete(id: Int): String
}