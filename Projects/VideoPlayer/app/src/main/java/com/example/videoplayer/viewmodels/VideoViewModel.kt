package com.example.videoplayer.viewmodels

import cafe.adriel.voyager.core.model.ScreenModel
import com.example.videoplayer.models.VideoFile
import com.example.videoplayer.repository.VideoFilesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VideoViewModel(private val repository: VideoFilesRepository) : ScreenModel {
    val state = MutableStateFlow<MutableList<VideoFile>>(mutableListOf())

    private var job : Job? = null

    fun getAllVideoFiles(){
        job?.cancel()
        job = CoroutineScope(Dispatchers.IO).launch {
            val newList = mutableListOf<VideoFile>()
            repository.getVideoFiles().collect{
                newList.add(it)
                state.update { state ->
                    newList
                }
            }
        }
    }
}

data class VideoState(
    val videoList : MutableList<VideoFile> = mutableListOf(),
)