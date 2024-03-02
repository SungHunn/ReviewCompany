package com.example.reviewcompany.presentation.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.reviewcompany.data.ArticleEntity
import com.example.reviewcompany.presentation.viewmodel.ArticleViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ArticleScreen(
    navController: NavController,
    auth: FirebaseAuth,
    viewModel: ArticleViewModel,
    articleEntity: ArticleEntity?
) {
    Text(text = articleEntity?.companyName!!)
}