package com.example.blog.model

data class PostResponse(
    val content:List<Post>,
	val currentPage:Int,
	val totalItems:Int,
	val totalPages:Int,
	val sortBy:String,
 	val sortDir:String
)