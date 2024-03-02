package com.example.reviewcompany.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.reviewcompany.presentation.screen.WritingScreen
import com.example.reviewcompany.presentation.screen.navigation.Screen
import com.example.reviewcompany.presentation.viewmodel.LoginViewModel
import com.example.reviewcompany.presentation.viewmodel.MainViewModel
import com.example.reviewcompany.presentation.viewmodel.SignUpViewModel
import com.example.reviewcompany.presentation.viewmodel.WritingViewModel
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var auth: FirebaseAuth

    private val signUpViewModel: SignUpViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val writingViewModel: WritingViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            false
        }
        setContent {
            ReviewCompanyTheme {
                val navController = rememberNavController()

                Log.e("auth", "${auth.currentUser?.uid}")

                mainViewModel.getArticle()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination =
                        if (auth.currentUser == null) {
                            Screen.Login.route
                        } else {
                            Screen.Main.route
                        }
                    ) {

                        composable(
                            route = Screen.Main.route
                        ) {
                            MainScreen(
                                navController,
                                auth,
                                firebaseList = mainViewModel.firebaseList
                            )
                        }

                        composable(
                            route = Screen.Login.route
                        ) {
                            LoginScreen(navController, loginViewModel)
                        }

                        composable(
                            route = Screen.SignUp.route
                        ) {
                            SignUpScreen(navController, signUpViewModel)
                        }

                        composable(
                            route = Screen.Writing.route
                        ) {
                            WritingScreen(navController, auth, writingViewModel)
                        }
                    }
                }
            }
        }
    }
}

