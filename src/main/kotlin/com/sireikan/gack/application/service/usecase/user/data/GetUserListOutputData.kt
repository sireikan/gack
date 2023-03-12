package com.sireikan.gack.application.service.usecase.user.data

class GetUserListOutputData private constructor(
    val userList: List<GetUserOutputData>,
) {
    companion object {
        fun create(userList: List<GetUserOutputData>): GetUserListOutputData {
            return GetUserListOutputData(userList)
        }
    }
}
