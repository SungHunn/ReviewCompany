package com.example.reviewcompany.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.reviewcompany.presentation.screen.LoginScreen
import com.example.reviewcompany.presentation.viewmodel.LoginViewModel
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity(){

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReviewCompanyTheme {
                val navController = rememberNavController()
                LoginScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}