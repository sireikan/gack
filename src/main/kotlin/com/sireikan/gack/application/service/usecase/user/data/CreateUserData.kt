package com.sireikan.gack.application.service.usecase.user.data

class CreateUserData private constructor(
    val userName: String,
) {
    companion object {
        fun create(userName: String): CreateUserData {
            return CreateUserData(userName)
        }
    }
}
