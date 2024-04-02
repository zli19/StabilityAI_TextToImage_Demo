package com.quentin.stabilityaidemo.service.retrofit

import android.content.Context
import android.util.Base64
import android.util.Log
import com.quentin.stabilityaidemo.model.request.RequestBody
import com.quentin.stabilityaidemo.model.request.TextPrompt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class StabilityAIPictureService(
    private val context: Context
) {
    private val engineId = "stable-diffusion-v1-6"
    private var fos: FileOutputStream? = null

    // Fetch picture URL based on a prompt
    suspend fun fetchPicture(prompt: String): String? {

        // Establish a ReqBody project.
        val requestBody = RequestBody(
            width = 1152,
            height = 896,
            text_prompts = listOf(TextPrompt(prompt))
        )

        return withContext(Dispatchers.IO) {

            val base64 = async {
                // Send a post request to the api with reqBody object.
                API.retrofit.getRecipePicture(engineId, requestBody)
            }
                .await()
                .awaitResponse()
                .body()
                ?.artifacts
                ?.get(0)
                ?.base64

            return@withContext writeToFile(base64) // Write to internal storage and return the path
        }
    }

    // Write to internal storage and return the path of the file
    private fun writeToFile(data: String?): String? {
        var returnValue: String? = null
        try {
            if(data != null){
                val decodedString: ByteArray = Base64.decode(data, Base64.DEFAULT)
                val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"))
                fos = context.openFileOutput("$time.png", Context.MODE_PRIVATE)
                fos?.write(decodedString);
                fos?.flush();
                fos?.close();
                returnValue = "${context.filesDir}${File.separator}$time.png"
                Log.i("writeToFile", returnValue)
            }
        }catch (e: Exception){
            Log.e("writeToFile", e.toString())
        }finally {
            if (fos != null) {
                fos = null;
            }
        }
        return  returnValue
    }
}