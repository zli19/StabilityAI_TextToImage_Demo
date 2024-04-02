package com.quentin.stabilityaidemo.model.request

//@JsonClass(generateAdapter = true)
data class RequestBody(
    val cfg_scale: Int = 7,
    val height: Int = 512,
    val samples: Int = 1,
    val steps: Int = 30,
    val text_prompts: List<TextPrompt>,
    val width: Int = 512
)