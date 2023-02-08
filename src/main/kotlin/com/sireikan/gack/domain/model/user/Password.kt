package com.sireikan.gack.domain.model.user

class Password {
    private var password: String

    constructor(password: String) {
        this.password = password
    }

    fun getValue(): String {
        return this.password
    }
}
