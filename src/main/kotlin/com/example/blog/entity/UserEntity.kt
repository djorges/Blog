package com.example.blog.entity

import com.example.blog.model.User
import javax.persistence.*

@Entity
@Table(
    name = "users",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["username"]),
        UniqueConstraint(columnNames = ["email"])
    ]
)
class UserEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long? = null,

    @Column(length = 60)
    val username:String,

    val email:String,

    var password:String,

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name="users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles:Set<RoleEntity> = HashSet()
){
    fun toModel():User{
        return User(
            id = id,
            username = username,
            email = email,
            password = password
        )
    }
}