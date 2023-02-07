package com.sireikan.gack.controller

import com.sireikan.gack.openapi.generated.controller.UserApi
import com.sireikan.gack.openapi.generated.model.MultipleUserResponse
import com.sireikan.gack.openapi.generated.model.UserResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController : UserApi {

    override fun getUser(): ResponseEntity<MultipleUserResponse> {
        return ResponseEntity(
            MultipleUserResponse(
                users = listOf(UserResponse(1, "name", "email", "password")),
            ),
            HttpStatus.OK
        )
    }
}
