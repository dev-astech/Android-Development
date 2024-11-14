package com.example.videoplayer.models

import android.net.Uri

data class VideoFile(
    val name : String,
    val duration : Long,
    val pathUri : Uri,
    val size : Long
)
