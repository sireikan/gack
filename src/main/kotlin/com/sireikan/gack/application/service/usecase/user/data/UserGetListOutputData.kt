package com.sireikan.gack.application.service.usecase.user.data

import com.sireikan.gack.application.service.usecase.user.data.UserData

class UserGetListOutputData {
    var userList: List<UserData>

    constructor(userList: List<UserData>) {
        this.userList = userList
    }
}
