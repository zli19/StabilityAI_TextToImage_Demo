package com.quentin.stabilityaidemo.model.request

import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class TextPrompt(
    val text: String
)