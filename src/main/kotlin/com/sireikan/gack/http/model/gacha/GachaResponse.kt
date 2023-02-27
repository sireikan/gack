package com.sireikan.gack.http.model.gacha

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonProperty
import com.sireikan.gack.openapi.generated.model.GachaInfoResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaCostResponse
import com.sireikan.gack.openapi.generated.model.MultipleGachaProbabilityResponse
import javax.validation.constraints.*
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.Valid

/**
 * 
 * @param gachaInfo 
 * @param gachaCosts 
 * @param gachaProbabilities 
 */
data class GachaResponse(

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("gacha_info") val gachaInfo: GachaInfoResponse? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("gacha_costs") val gachaCosts: MultipleGachaCostResponse? = null,

    @field:Valid
    @Schema(example = "null", description = "")
    @get:JsonProperty("gacha_probabilities") val gachaProbabilities: MultipleGachaProbabilityResponse? = null
) {

}

