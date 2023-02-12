package com.sireikan.gack.http.model.user

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 *
 * @param name
 * @param email
 * @param password
 */
data class UserRequest(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("name")
    val name: kotlin.Any? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("email")
    val email: kotlin.Any? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("password")
    val password: kotlin.Any? = null,
)
