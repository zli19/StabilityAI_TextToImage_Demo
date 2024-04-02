package com.quentin.stabilityaidemo

import android.app.Application
import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quentin.stabilityaidemo.service.retrofit.StabilityAIPictureService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel(){
    var img by mutableStateOf<String?>(null)
    var aiService = StabilityAIPictureService(application.applicationContext)


    fun getPicture(prompt: String) {
        if(prompt.isBlank()) return
        viewModelScope.launch(Dispatchers.IO) {
            aiService.fetchPicture(prompt).apply {
                img = this
            }
        }
    }
}