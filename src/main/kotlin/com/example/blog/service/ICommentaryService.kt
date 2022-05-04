package com.example.blog.service

import com.example.blog.model.Commentary

interface ICommentaryService {
    fun create(postId:Int,commentary: Commentary): Commentary
    fun getById(postId:Int,commentaryId:Int):Commentary
    fun update(postId:Int,commentary: Commentary):Commentary
    fun delete(postId:Int,commentaryId:Int): String
    fun getCommentariesByPostId(postId:Int):List<Commentary>
}