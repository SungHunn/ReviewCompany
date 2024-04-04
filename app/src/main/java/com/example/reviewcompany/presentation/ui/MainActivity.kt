package com.example.reviewcompany.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.reviewcompany.presentation.screen.navigation.navGraph
import com.example.reviewcompany.presentation.viewmodel.ArticleViewModel
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
    private val articleViewModel: ArticleViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            false
        }
        setContent {
            ReviewCompanyTheme {
                val navController = rememberNavController()

                Log.e("auth", "${auth.currentUser?.uid}")



                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navGraph(
                        auth = auth,
                        mainViewModel = mainViewModel,
                        loginViewModel = loginViewModel,
                        signUpViewModel = signUpViewModel,
                        writingViewModel = writingViewModel,
                        articleViewModel = articleViewModel
                    )
                }
            }
        }
    }
}

