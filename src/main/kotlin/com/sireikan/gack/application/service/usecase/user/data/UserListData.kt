package com.sireikan.gack.application.service.usecase.user.data

class UserListData private constructor(
    val userList: List<UserData>,
) {
    companion object {
        fun create(userList: List<UserData>): UserListData {
            return UserListData(userList)
        }
    }
}
