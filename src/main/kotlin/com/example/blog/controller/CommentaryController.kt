package com.example.blog.controller

import com.example.blog.model.Commentary
import com.example.blog.service.ICommentaryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/")
class CommentaryController {
    @PostMapping("/posts/{postId}/commentaries")
    fun create(
        @PathVariable("postId") postId:Int,
        @Valid @RequestBody commentary:Commentary
    ): Commentary {
        return service.create(postId,commentary)
    }

    @PutMapping("/posts/{postId}/commentaries")
    fun update(
        @PathVariable("postId") postId:Int,
        @Valid @RequestBody commentary:Commentary
    ): Commentary{
        return service.update(postId,commentary)
    }

    @GetMapping("/posts/{postId}/commentaries")
    fun getAllByPostId(@PathVariable("postId") postId:Int): List<Commentary>{
        return service.getCommentariesByPostId(postId)
    }

    @GetMapping("/posts/{postId}/commentaries/{commentId}")
    fun getById(
        @PathVariable("postId") postId:Int,
        @PathVariable("commentId") commentId:Int
    ): Commentary{
        return service.getById(postId,commentId)
    }

    @DeleteMapping("/posts/{postId}/commentaries/{commentId}")
    fun delete(
        @PathVariable("postId") postId:Int,
        @PathVariable("commentId") commentId:Int
    ):String{
        return service.delete(postId,commentId)
    }

    @Autowired
    private lateinit var service: ICommentaryService
}