package com.fooddonation.aniltalariS3175001

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fooddonation.aniltalariS3175001.ui.theme.FoodDonationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodDonationTheme {
                IntroScreen()
            }
        }
    }
}

@Composable
fun IntroScreen() {
    var showSplash by remember { mutableStateOf(true) }

    val context = LocalContext.current as Activity

    LaunchedEffect(Unit) {
        delay(3000)
        showSplash = false

    }

        if (showSplash) {
            IntroScreenD()
        } else {
            val DonorStatus = DonorDetails.getDonorStatus(context)

            if (DonorStatus) {


                context.startActivity(Intent(context, ContainerActivity::class.java))
                context.finish()
            } else {
                context.startActivity(Intent(context, AccountAccessActivity::class.java))
                context.finish()
            }
        }





}


@Composable
fun IntroScreenD() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Welcome to\nAnil Talari DonorDish App",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF5D3FD3),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ic_fooddonation), contentDescription = "Food",

            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Share a Meal\nSpread Kindness",
            style = MaterialTheme.typography.headlineLarge,
            color = Color(0xFF5D3FD3),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

    }
}


@Preview(showBackground = true)
@Composable
fun IntroScreenDPreview() {
    IntroScreenD()
}