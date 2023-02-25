package com.sireikan.gack.application.service.usecase.user.data

import com.sireikan.gack.domain.model.user.User

class UserData {
    var userId: Int
    var userName: String
    var email: String
    var password: String

    constructor(user: User) {
        this.userId = user.id.userId
        this.userName = user.name.userName
        this.email = user.email.email
        this.password = user.password.password
    }
}
