package com.example.blog.repository

import com.example.blog.entity.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IRoleRepository  : JpaRepository<RoleEntity, Long> {
    fun findByName(name:String):RoleEntity
}