package com.example.reviewcompany.presentation.screen.navigation

sealed class Screen (val route: String) {
    object Main: Screen(route = "main_screen")
    object Login: Screen(route = "login_screen")
    object SignUp: Screen(route = "signup_screen")

}