package com.example.genai.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.genai.ChatData
import com.example.genai.ChatRoleEnum

@Composable
fun ChatList(
    list: MutableList<ChatData>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        items(list) {
            if (it.role == ChatRoleEnum.USER.role) {
                UserMessage(message = it.message)
//                Text(
//                    text = it.message,
//                    textAlign = TextAlign.Right,
//                    modifier = Modifier
//                        .padding(4.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                        .background(Color(0xFFC5E2D1))
//                        .fillMaxWidth()
//                        .padding(8.dp),
//                    color = Color.Black,
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.SemiBold
//                )
            }else{
                BotMessage(message = it.message)
//                Text(
//                    text = it.message,
//                    textAlign = TextAlign.Left,
//                    modifier = Modifier
//                        .padding(4.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                        .background(Color(0xFFC5E2D1))
//                        .fillMaxWidth()
//                        .padding(8.dp),
//                    color = Color.Black,
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Normal
//                )
            }
        }
    }
}