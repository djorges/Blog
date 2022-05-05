package com.example.blog.controller

import com.example.blog.entity.RoleEntity
import com.example.blog.exception.InvalidOperationException
import com.example.blog.model.User
import com.example.blog.repository.IRoleRepository
import com.example.blog.repository.IUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/auth")
class UserController {
    @Autowired
    private lateinit var authManager: AuthenticationManager

    @Autowired
    private lateinit var userRepository:IUserRepository

    @Autowired
    private lateinit var roleRepository: IRoleRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @PostMapping("/login")
    fun login(@RequestBody user: User):ResponseEntity<String>{
        //Authenticate user
        val auth = authManager.authenticate(
            UsernamePasswordAuthenticationToken(user.email,user.password)
        )
        SecurityContextHolder.getContext().authentication = auth

        return ResponseEntity("Logged in successfully",HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(@RequestBody user: User):User{
        //Check if user already exists in db
        if(userRepository.existsByUsername(user.username.orEmpty()) || userRepository.existsByEmail(user.email))
        {
            throw InvalidOperationException("User already exists")
        }

        //Set password encrypted and default roles
        val entity = user.toEntity().apply {
            password = passwordEncoder.encode(password)

            val role = roleRepository.findByName("ROLE_ADMIN")
            roles = HashSet<RoleEntity>().apply { add(role) }
        }
        return userRepository.save(entity).toModel()
    }
}