package com.example.reviewcompany.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reviewcompany.presentation.screen.LoginScreen
import com.example.reviewcompany.presentation.screen.MainScreen
import com.example.reviewcompany.presentation.screen.SignUpScreen
import com.example.reviewcompany.presentation.screen.navigation.Screen
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            false
        }
        setContent {
            ReviewCompanyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Main.route
                    ) {

                        composable(
                            route = Screen.Main.route
                        ) {
                            MainScreen(navController)
                        }

                        composable(
                            route = Screen.Login.route
                        ) {
                            LoginScreen(navController)
                        }

                        composable(
                            route = Screen.SignUp.route
                        ) {
                            SignUpScreen(navController)
                        }

                    }

                }
            }
        }
    }
}

