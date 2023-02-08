package com.sireikan.gack.domain.model.user

class Email {
    private var email: String

    constructor(email: String) {
        this.email = email
    }

    fun getValue(): String {
        return this.email
    }
}
