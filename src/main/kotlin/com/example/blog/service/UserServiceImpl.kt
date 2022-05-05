package com.example.blog.service

import com.example.blog.entity.RoleEntity
import com.example.blog.exception.EntityNotFoundException
import com.example.blog.repository.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service


@Service
class UserServiceImpl: IUserService {
    override fun loadUserByUsername(email: String?): UserDetails {
        val user = repository.findByEmail(email.orEmpty())
            .orElseThrow { EntityNotFoundException("User","email") }

        return User(
            user.email,
            user.password,
            mapRoles(user.roles)
        )
    }

    private fun mapRoles(roles:Set<RoleEntity>): Collection<GrantedAuthority>{
        return roles.map { SimpleGrantedAuthority(it.name) }
    }

    @Autowired
    private lateinit var repository:IUserRepository
}