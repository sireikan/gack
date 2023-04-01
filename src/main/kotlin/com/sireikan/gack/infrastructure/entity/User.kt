package com.sireikan.gack.infrastructure.entity

import com.sireikan.gack.infrastructure.error.RepositoryException

class User private constructor(
    val id: Long?,
    val userId: Long,
    val name: String,
    val created: String?,
) {
    companion object {
        fun create(id: Long?, userId: Long, name: String, created: String?): User {
            return User(
                id,
                userId,
                name,
                created,
            )
        }
    }

    init {
        if (name.isEmpty()) {
            throw RepositoryException("User is invalid.")
        }
    }
}
