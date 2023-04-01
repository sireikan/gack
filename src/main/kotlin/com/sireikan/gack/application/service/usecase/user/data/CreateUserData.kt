package com.sireikan.gack.application.service.usecase.user.data

class CreateUserData private constructor(
    val userId: Long,
    val userName: String,
) {
    companion object {
        fun create(userId: Long, userName: String): CreateUserData {
            return CreateUserData(userId, userName)
        }
    }
}
