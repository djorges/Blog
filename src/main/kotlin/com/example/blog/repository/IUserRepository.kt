package com.example.blog.repository

import com.example.blog.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IUserRepository: JpaRepository<UserEntity,Long> {
    fun findByEmail(email:String): Optional<UserEntity>
    fun findByUsername(username:String):Optional<UserEntity>
    fun existsByUsername(username: String):Boolean
    fun existsByEmail(email: String):Boolean
}