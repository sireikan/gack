package com.sireikan.gack.module.infrastructure.entity

class User {
    private var id: Int
    private var name: String
    private var email: String
    private var password: String

    constructor(id: Int, name: String, email: String, password: String) {
        this.id = id
        this.name = name
        this.email = email
        this.password = password
    }
}
