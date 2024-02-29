package com.example.reviewcompany.domain.repository

import com.example.reviewcompany.data.ArticleEntity

interface FirebaseRepository {

    suspend fun logIn(email: String, password: String) : Boolean

    suspend fun signUp(email: String, password: String): Boolean

    suspend fun insertArticle(articleEntity: ArticleEntity) : Boolean


}