package com.sireikan.gack.application.service.usecase.user.data

class UserGetListOutputData {
    var userList: List<UserData>

    constructor(userList: List<UserData>) {
        this.userList = userList
    }
}
