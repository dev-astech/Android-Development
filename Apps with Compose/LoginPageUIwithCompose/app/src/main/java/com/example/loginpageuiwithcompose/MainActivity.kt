package com.example.loginpageuiwithcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.loginpageuiwithcompose.ui.theme.LoginPageUIWithComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPageUIWithComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginPage("Android")
                }
            }
        }
    }
}

@Composable
fun LoginPage(name: String, modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(300.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile",
                modifier = Modifier
                    .size(100.dp)
            )
            OutlinedTextField(
                value = email, onValueChange = { email = it },
                label = { Text(text = "Email") },
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .fillMaxWidth(1f),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Gray
                ),
                maxLines = 1,
                singleLine = true

            )
            OutlinedTextField(
                value = password, onValueChange = { password = it },
                label = { Text(text = "Password") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth(1f),
                maxLines = 1,
                singleLine = true
            )
            Text(
                text = "Forgot Password?",
                color = Color.Blue,
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Right
            )
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .size(width = 250.dp, height = 50.dp)
                    .padding(5.dp)
            ) {
                Text(text = "Login")
            }
            Text(text = "Register", color = Color.Blue, modifier = Modifier.padding(top = 5.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LoginPageUIWithComposeTheme {
        LoginPage("Android")
    }
}