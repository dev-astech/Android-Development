package com.example.genai.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@Composable
fun ChatFooter(
    onClick: (text: String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var inputText by remember {
        mutableStateOf("")
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = {
                inputText = it
            },
            placeholder = {
                Text(text = "Enter Your Query Here")
            },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .background(Color.Transparent)
                .clip(RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp)
        )

        IconButton(onClick = {
            onClick(inputText)
            inputText = ""
            keyboardController?.hide()
        }) {
            Icon(
                Icons.Rounded.Send,
                contentDescription = "null",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF03be6f))
                    .padding(8.dp),
                tint = Color.Black,

            )
        }
    }
}