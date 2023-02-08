package com.sireikan.gack.application.service.user.common

import com.sireikan.gack.domain.model.user.User

class UserData {
    var userId: Int
    var userName: String
    var email: String
    var password: String

    constructor(user: User) {
        this.userId = user.id.getValue()
        this.userName = user.name.getValue()
        this.email = user.email.getValue()
        this.password = user.password.getValue()
    }
}
