package com.aniltalari.fooddonation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class AccountAccessActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AccountAccessActivityScreen()
        }
    }
}

@Composable
fun AccountAccessActivityScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Login Now",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF5D3FD3),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 12.dp)
        )

        Text(
            text = "Please login or sign up to continue using\nour app",
            color = Color.Black,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_fooddonation), contentDescription = "Food",

            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        )



        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(50.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.LightGray, MaterialTheme.shapes.medium)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (email.isEmpty()) {
                        Text(text = "Email", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        BasicTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(50.dp),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.LightGray, MaterialTheme.shapes.medium)
                        .padding(horizontal = 16.dp)
                        .fillMaxSize()
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (password.isEmpty()) {
                            Text(text = "Password", color = Color.Gray)
                        }
                        innerTextField()
                    }

                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                context.startActivity(Intent(context, ContainerActivity::class.java))
                context.finish()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(Color(0xFF5D3FD3))
        ) {
            Text(
                text = "Login", color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Don’t have an account?")
            Text(
                text = " Sign up",
                color = Color(0xFF5D3FD3),
                modifier = Modifier.clickable {
                    context.startActivity(Intent(context, SetupAccountActivity::class.java))
                    context.finish()
                },
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AccountAccessActivityScreenPreview() {
    AccountAccessActivityScreen()
}