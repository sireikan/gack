package com.sireikan.gack.http.model.gacha

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.*
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 * 
 * @param costs 
 */
data class MultipleGachaCostResponse(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("costs") val costs: kotlin.Any? = null
) {

}

