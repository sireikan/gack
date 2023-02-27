package com.sireikan.gack.http.model.gacha

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.*
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 * 
 * @param probabilities 
 */
data class MultipleGachaProbabilityResponse(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("probabilities") val probabilities: kotlin.Any? = null
) {

}

