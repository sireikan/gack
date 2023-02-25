package com.sireikan.gack.infrastructure.entity

class User private constructor (
    val id: Int,
    val name: String,
    val email: String,
    val password: String
) {
    companion object {
        fun create(id: Int, name: String, email: String, password: String): User {
            return User(
                id,
                name,
                email,
                password
            )
        }
    }
}
