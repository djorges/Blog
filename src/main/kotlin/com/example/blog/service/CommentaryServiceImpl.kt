package com.example.blog.service

import com.example.blog.entity.CommentaryEntity
import com.example.blog.exception.EntityNotFoundException
import com.example.blog.exception.InvalidOperationException
import com.example.blog.model.Commentary
import com.example.blog.repository.ICommentaryRepository
import com.example.blog.repository.IPostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentaryServiceImpl :ICommentaryService{
    override fun create(postId:Int,commentary: Commentary): Commentary {
        //Get post
        val post = postRepository.findById(postId)
            .orElseThrow { EntityNotFoundException("Post", "id", postId) }

        //Set post to commentary
        val entity = commentary.toEntity()
        entity.post = post

        return commentRepository.save(entity).toModel()
    }

    override fun getCommentariesByPostId(postId: Int): List<Commentary> {
        return commentRepository.findByPostId(postId)
            .map { it.toModel() }
    }

    override fun getById(postId: Int, commentaryId: Int): Commentary {

        val commentary = getEntityById(postId, commentaryId)

        return commentary.toModel()
    }

    override fun update(postId: Int, commentary: Commentary): Commentary {
        val entity = getEntityById(postId, commentary.id!!)
        //
        entity.apply{
            title = commentary.title
            email = commentary.email
            content = commentary.content
        }

        return commentRepository.save(entity).toModel()
    }

    override fun delete(postId: Int, commentaryId: Int):String {
        getEntityById(postId, commentaryId)

        commentRepository.deleteById(commentaryId)

        return "Commentary deleted successfully"
    }

    private fun getEntityById(postId: Int, commentaryId: Int): CommentaryEntity {
        val post = postRepository.findById(postId)
            .orElseThrow { EntityNotFoundException("Post", "id", postId) }
        val commentary = commentRepository.findById(commentaryId)
            .orElseThrow { EntityNotFoundException("Commentary", "id", commentaryId) }

        //
        if (commentary.post!!.id!! != post.id) {
            throw InvalidOperationException("The commentary does not belong to this post")
        }
        return commentary
    }

    @Autowired
    private lateinit var commentRepository:ICommentaryRepository

    @Autowired
    private lateinit var postRepository: IPostRepository
}