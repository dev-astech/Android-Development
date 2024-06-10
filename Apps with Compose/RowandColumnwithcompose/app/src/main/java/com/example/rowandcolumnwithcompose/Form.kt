package com.example.rowandcolumnwithcompose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Form() {
    val context = LocalContext.current
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }
    var country by rememberSaveable { mutableStateOf("") }
    Image(
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.img),
        contentDescription = ""
    )
    Column(Modifier.padding(8.dp)) {
        Row {
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                modifier = Modifier
                    .padding(end = 5.dp)
                    .weight(.5f),
                label = {Text(text = "First Name")}
            )
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                modifier = Modifier.weight(.5f),
                label = { Text(text = "Last Name")}
            )
        }
        TextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "DD-MM-YYYY")},
            label = {Text(text = "Date of Birth")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Row(Modifier.padding(top = 5.dp)) {
            TextField(
                value = country,
                onValueChange = { country = it },
                modifier = Modifier
                    .weight(.5f)
                    .padding(end = 5.dp),
                label = { Text(text = "Country")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Button(
                onClick = { Toast.makeText(context,"Coming Soon", Toast.LENGTH_LONG).show() },
                Modifier.weight(.5f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
            ) {
                Text(text = "Apply")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormPreview() {
    Form()
}