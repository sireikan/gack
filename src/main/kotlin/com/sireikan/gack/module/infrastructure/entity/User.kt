package com.sireikan.gack.module.infrastructure.entity

class User {
    var id: Int
    var name: String
    var email: String
    var password: String

    constructor(id: Int, name: String, email: String, password: String) {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
    }
}
