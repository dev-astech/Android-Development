package com.example.genai

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatBotVM : ViewModel(){

    val chatList by lazy {
        mutableStateListOf<ChatData>()
    }

    private val genAI by lazy {
        GenerativeModel(
            modelName = "gemini-pro",
            apiKey = apiKey
        )
    }

    fun sendMessage(message : String) = viewModelScope.launch {
        val chat = genAI.startChat()
        chatList.add(ChatData(message, ChatRoleEnum.USER.role))
        chat.sendMessage(
            content(ChatRoleEnum.USER.role) {
                text(message)
            }
        ).text?.let {
            chatList.add(ChatData(it, ChatRoleEnum.MODEL.role))
        }

//        val response = genAI.startChat().sendMessage(prompt = message).text
//        Log.d("AI Response", response.toString())
    }
}