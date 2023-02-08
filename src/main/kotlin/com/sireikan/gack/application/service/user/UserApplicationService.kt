package com.sireikan.gack.application.service.user

import com.sireikan.gack.application.service.user.getlist.UserGetListOutputData

interface UserApplicationService {
    fun getList(): UserGetListOutputData
}
