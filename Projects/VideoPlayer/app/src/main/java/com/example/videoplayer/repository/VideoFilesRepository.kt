package com.example.videoplayer.repository

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore.Video.Media
import com.example.videoplayer.models.VideoFile
import kotlinx.coroutines.flow.flow

class VideoFilesRepository(private val context: Context) {
    fun getVideoFiles() = flow<VideoFile> {
        val contentResolver = context.contentResolver ?: return@flow

        val projection = arrayOf(
            Media.DISPLAY_NAME,
            Media.DURATION,
            Media.SIZE,
            Media._ID
        )

        contentResolver.query(
            Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            "${Media.DATE_ADDED} DESC"
        )?.use { cursor->
            while (cursor.moveToNext()){
                val displayNameIndex = cursor.getColumnIndexOrThrow(Media.DISPLAY_NAME)
                val durationIndex = cursor.getColumnIndexOrThrow(Media.DURATION)
                val sizeIndex = cursor.getColumnIndexOrThrow(Media.SIZE)
                val idIndex = cursor.getColumnIndexOrThrow(Media._ID)

                val displayName = cursor.getString(displayNameIndex)
                val duration = cursor.getLong(durationIndex)
                val size = cursor.getLong(sizeIndex)
                val id = cursor.getLong(idIndex)


                val videoUri = ContentUris.withAppendedId(Media.EXTERNAL_CONTENT_URI, id)

                val videoFile = VideoFile(
                    name = displayName,
                    duration = duration,
                    pathUri = videoUri,
                    size = size
                )

                emit(videoFile)
            }
        }
    }
}