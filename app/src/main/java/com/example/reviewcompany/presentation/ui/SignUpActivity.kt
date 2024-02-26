package com.example.reviewcompany.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.reviewcompany.presentation.screen.SignUpScreen
import com.example.reviewcompany.presentation.viewmodel.SignUpViewModel
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : ComponentActivity(){

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReviewCompanyTheme {
                val navController = rememberNavController()
                SignUpScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}