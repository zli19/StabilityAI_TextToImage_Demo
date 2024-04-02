package com.quentin.stabilityaidemo.service.retrofit

import com.quentin.stabilityaidemo.BuildConfig
import com.quentin.stabilityaidemo.model.request.RequestBody
import com.quentin.stabilityaidemo.model.response.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface StabilityAPI {
    @Headers(
        "Authorization: Bearer ${BuildConfig.STABILITYAI_API_KEY}",
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("{engineId}/text-to-image")
    fun getRecipePicture(
        @Path("engineId") engineId: String,
        @Body requestBody: RequestBody
    ): Call<ResponseBody>
}