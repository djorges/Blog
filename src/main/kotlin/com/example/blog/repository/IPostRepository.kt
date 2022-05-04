package com.example.blog.repository

import com.example.blog.entity.PostEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IPostRepository : JpaRepository<PostEntity,Int>