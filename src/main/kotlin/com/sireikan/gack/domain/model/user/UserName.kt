package com.sireikan.gack.domain.model.user

class UserName {
    private var userName: String

    constructor(userName: String) {
        this.userName = userName
    }

    fun getValue(): String {
        return this.userName
    }
}
