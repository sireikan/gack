package com.sireikan.gack.application.service.user.getlist

import com.sireikan.gack.application.service.user.common.UserData

class UserGetListOutputData {
    var userList: List<UserData>

    constructor(userList: List<UserData>) {
        this.userList = userList
    }
}
