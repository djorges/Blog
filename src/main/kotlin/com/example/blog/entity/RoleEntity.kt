package com.example.blog.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "roles")
class RoleEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long,

    @Column(length = 60)
    val name:String
)