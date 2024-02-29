package com.example.reviewcompany.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reviewcompany.presentation.screen.WritingScreen
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme

class WritingActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            ReviewCompanyTheme {
                val navController = rememberNavController()

                WritingScreen(navController = navController)
            }
        }
    }
}