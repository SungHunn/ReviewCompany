package com.example.reviewcompany.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.reviewcompany.R
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReviewCompanyTheme {
                SplashScreen(
                    move = {
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                )
            }
        }
    }
}


@Composable
private fun SplashScreen(
    move: () -> Unit
) {
    Button(onClick = { move }) {
        Text(text = "Button")
    }

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFF89DE41)),
//        contentAlignment = Alignment.Center
//    )
//    {
//        Image(
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = "logo"
//        )
//
//    }


}