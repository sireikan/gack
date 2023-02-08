package com.sireikan.gack.domain.model.user

class User {
    var id: UserId
    var name: UserName
    var email: Email
    var password: Password

    constructor(id: UserId, name: UserName, email: Email, password: Password) {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
    }
}
