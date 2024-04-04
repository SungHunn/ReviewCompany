package com.example.reviewcompany.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.reviewcompany.domain.model.DomainArticle
import com.example.reviewcompany.presentation.screen.ArticleScreen
import com.example.reviewcompany.presentation.viewmodel.ArticleViewModel
import com.example.reviewcompany.ui.theme.ReviewCompanyTheme
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticleActivity : ComponentActivity() {

    @Inject
    lateinit var auth: FirebaseAuth

    private val viewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReviewCompanyTheme() {
                val navController = rememberNavController()


                ArticleScreen(
                    navController = navController,
                    auth = auth,
                    viewModel = viewModel,
                    articleEntity = DomainArticle()
                )
            }
        }


    }
    
}

