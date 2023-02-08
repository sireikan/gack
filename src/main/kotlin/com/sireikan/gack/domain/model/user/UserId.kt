package com.sireikan.gack.domain.model.user

class UserId {
    private var userId: Int

    constructor(userId: Int) {
        this.userId = userId
    }

    fun getValue(): Int {
        return this.userId
    }
}
