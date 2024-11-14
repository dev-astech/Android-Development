package com.example.genai

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.genai.components.ChatFooter
import com.example.genai.components.ChatHeader
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.genai.components.ChatList
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChatBot(
    viewModel: ChatBotVM = viewModel(),
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(Color(0xffe9f5ed))
    ) {
        ChatHeader()

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            if (viewModel.chatList.isEmpty()) {
                Text(
                    text = "No Chat Available",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                ChatList(list = viewModel.chatList)
            }
        }

        ChatFooter {
            if (it.isNotEmpty()) {
                viewModel.sendMessage(it)
            }
        }
    }
}