package com.sireikan.gack.application.service.usecase.user.data

class UserGetListOutputData private constructor(
    val userList: List<UserData>,
) {
    companion object {
        fun create(userList: List<UserData>): UserGetListOutputData {
            return UserGetListOutputData(userList)
        }
    }
}
