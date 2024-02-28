package com.example.reviewcompany.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme

class WritingActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReviewCompanyTheme {

            }
        }
    }
}