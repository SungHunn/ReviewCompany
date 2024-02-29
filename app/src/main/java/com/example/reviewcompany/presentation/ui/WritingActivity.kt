package com.example.reviewcompany.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.reviewcompany.presentation.screen.WritingScreen
import com.example.reviewcompany.presentation.viewmodel.WritingViewModel
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WritingActivity: ComponentActivity() {

    @Inject
    lateinit var auth: FirebaseAuth

    private val viewModel: WritingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            ReviewCompanyTheme {
                val navController = rememberNavController()

                WritingScreen(navController = navController, auth, viewModel)
            }
        }
    }
}