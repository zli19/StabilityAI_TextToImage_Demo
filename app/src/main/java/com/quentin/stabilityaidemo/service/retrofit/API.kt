package com.quentin.stabilityaidemo.service.retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object API {
    private const val STABILITY_BASE_URL = "https://api.stability.ai/v1/generation/"

    val retrofit: StabilityAPI by lazy {
        Retrofit.Builder()
            .baseUrl(STABILITY_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(StabilityAPI::class.java)
    }
}