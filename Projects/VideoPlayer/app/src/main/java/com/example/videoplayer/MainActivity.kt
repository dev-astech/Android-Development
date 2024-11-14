package com.example.videoplayer

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator


import com.example.videoplayer.screens.VideoListScreen
import com.example.videoplayer.ui.theme.VideoPlayerTheme
import com.example.videoplayer.utils.askPermissions
import com.example.videoplayer.utils.checkPermissions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isGranted = remember {
                mutableStateOf(false)
            }
            VideoPlayerTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {

                        val permissions =
                            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
                                android.Manifest.permission.READ_MEDIA_VIDEO
                            }else{
                                android.Manifest.permission.READ_EXTERNAL_STORAGE
                            }

                        isGranted.value = checkPermissions(permissions)

                        if(isGranted.value){
                            Navigator(
                                screen = VideoListScreen(modifier = Modifier)
                            )
                        }else{
                            Button(
                                onClick = {
                                    askPermissions(
                                        permissions,
                                        onPermissionGranted = {
                                            isGranted.value = true
                                        },
                                        onPermissionDenied = {
                                            isGranted.value = false
                                            Toast.makeText(this@MainActivity, "Allow Permission", Toast.LENGTH_SHORT).show()
                                        },
                                        onPermissionsResultCallback = {})
                                },
                                modifier = Modifier
                                    .align(Alignment.Center)
                            ) {
                                Text(text = "Allow Permission")
                            }
                        }
                    }
                }
            }
        }
    }
}