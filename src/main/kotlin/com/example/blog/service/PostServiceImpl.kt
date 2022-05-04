package com.example.blog.service

import com.example.blog.exception.EntityNotFoundException
import com.example.blog.model.Post
import com.example.blog.repository.IPostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class PostServiceImpl :IPostService {
    @Autowired
    private lateinit var repository: IPostRepository

    override fun create(post: Post): Post {
        return repository.save(post.toPostEntity()).toPostModel()
    }

    override fun getById(id:Int):Post{
        return repository.findById(id)
            .orElseThrow{ EntityNotFoundException("Post","id",id)}
            .toPostModel()
    }

    override fun update(post: Post):Post {
        //update post if exists in db
        getById(post.id!!)

        return repository.save(post.toPostEntity()).toPostModel()
    }
    override fun delete(id:Int):String{
        //delete post if exists in db
        val model = getById(id)

        repository.delete(model.toPostEntity())
        return "Post deleted successfully."
    }

    override fun getAll(page: Int, pageSize: Int, sortBy: String, sortDir: String): Page<Post> {
        //Set sort
        val s: Sort = Sort.by(sortBy)
        val sort: Sort = if (sortDir.equals(Sort.Direction.ASC.name, ignoreCase = true)) s
            .ascending() else s.descending()
        //Set page request
        val pageable = PageRequest.of(page - 1,pageSize,sort)

        return repository.findAll(pageable)
            .map { it.toPostModel() }
    }
}