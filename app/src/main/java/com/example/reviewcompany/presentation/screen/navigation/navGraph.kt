package com.example.reviewcompany.presentation.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.reviewcompany.domain.model.DomainArticle
import com.example.reviewcompany.presentation.screen.ArticleScreen
import com.example.reviewcompany.presentation.screen.LoginScreen
import com.example.reviewcompany.presentation.screen.MainScreen
import com.example.reviewcompany.presentation.screen.SignUpScreen
import com.example.reviewcompany.presentation.screen.WritingScreen
import com.example.reviewcompany.presentation.viewmodel.ArticleViewModel
import com.example.reviewcompany.presentation.viewmodel.LoginViewModel
import com.example.reviewcompany.presentation.viewmodel.MainViewModel
import com.example.reviewcompany.presentation.viewmodel.SignUpViewModel
import com.example.reviewcompany.presentation.viewmodel.WritingViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun navGraph(
    auth : FirebaseAuth,
    mainViewModel: MainViewModel,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    writingViewModel: WritingViewModel,
    articleViewModel: ArticleViewModel

){
    val navController = rememberNavController()

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
            //mainViewModel.getArticle()

            MainScreen(
                navController,
                auth,
                mainViewModel,
                idList = mainViewModel.firebaseId
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

        composable(
            route = Screen.Article.route
        ) {
            val article = navController.previousBackStackEntry?.savedStateHandle?.get<DomainArticle>("article")
            ArticleScreen(navController, auth, articleViewModel, article)
        }
    }
}