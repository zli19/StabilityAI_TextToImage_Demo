package com.quentin.stabilityaidemo

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import coil.compose.AsyncImage
import com.quentin.stabilityaidemo.ui.theme.StabilityAIDemoTheme

class MainActivity : ComponentActivity() {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StabilityAIDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Local variable to store input from TextField
                    var prompt by remember {
                        mutableStateOf("")
                    }

                    // Initiate view model
                    mainViewModel = MainViewModel(application)

                    Column {
                        TextField(
                            enabled = true,
                            value = prompt,
                            onValueChange = {
                                prompt = it
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.25f)
                        )

                        Button(onClick = {
                            // Get picture
                            mainViewModel.getPicture(prompt)
                        }) {
                            Text(text = "Get picture")
                        }
                        MyImage(mainViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun MyImage(mainViewModel: MainViewModel){
    AsyncImage(
        model = mainViewModel.img,
        contentDescription = null
    )
}
