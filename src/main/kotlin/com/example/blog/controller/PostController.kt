package com.example.blog.controller

import com.example.blog.model.Post
import com.example.blog.model.PostResponse
import com.example.blog.service.IPostService
import com.example.blog.utils.Constants.DEFAULT_PAGE_NUMBER
import com.example.blog.utils.Constants.DEFAULT_PAGE_SIZE
import com.example.blog.utils.Constants.DEFAULT_SORT_BY
import com.example.blog.utils.Constants.DEFAULT_SORT_DIRECTION
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/post")
@CrossOrigin
class PostController {
    @GetMapping("/")
    fun listAll(
        @RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER, required = false) page:Int,
        @RequestParam(value = "pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) pageSize:Int,
        @RequestParam(value = "sortBy", defaultValue = DEFAULT_SORT_BY, required = false) sortBy:String,
        @RequestParam(value = "sortDir", defaultValue = DEFAULT_SORT_DIRECTION, required = false) sortDir:String,
    ):PostResponse{
        //Get pageable
        val pageable = service.getAll(page, pageSize, sortBy, sortDir)

        //Create response
        val response = PostResponse(
            content= pageable.content,
            currentPage = pageable.number,
            totalItems = pageable.numberOfElements,
            totalPages = pageable.totalPages,
            sortBy = sortBy,
            sortDir = sortDir
        )
        return response
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    fun create(@Valid @RequestBody post: Post): Post {
        return service.create(post)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id:Int):Post{
        return service.getById(id)
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    fun update(@Valid @RequestBody post: Post): Post{
        return service.update(post)
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id:Int): String{
        return service.delete(id)
    }


    @Autowired
    private lateinit var service: IPostService
}