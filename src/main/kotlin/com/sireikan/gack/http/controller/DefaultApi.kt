/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
*/
package com.sireikan.gack.http.controller

import com.sireikan.gack.http.model.user.UserRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Validated
@RequestMapping("\${api.base-path:}")
interface DefaultApi {

    @Operation(
        summary = "",
        operationId = "deleteUserId",
        description = "delete user",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
        ],
    )
    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/user/{id}"],
    )
    fun deleteUserId(
        @Parameter
        (description = "user id", required = true)
        @PathVariable("id")
        id: kotlin.Any,
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }

    @Operation(
        summary = "",
        operationId = "putUserId",
        description = "update user",
        responses = [
            ApiResponse(responseCode = "200", description = "OK"),
        ],
    )
    @RequestMapping(
        method = [RequestMethod.PUT],
        value = ["/user/{id}"],
        consumes = ["application/json"],
    )
    fun putUserId(
        @Parameter(description = "user id", required = true)
        @PathVariable("id")
        id: kotlin.Any,
        @Parameter(description = "")
        @Valid
        @RequestBody(required = false)
        userRequest: UserRequest?,
    ): ResponseEntity<Unit> {
        return ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
    }
}
