package com.quentin.stabilityaidemo.model.response

import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class Artifact(
    val base64: String,
    val finishReason: String,
    val seed: Long
)