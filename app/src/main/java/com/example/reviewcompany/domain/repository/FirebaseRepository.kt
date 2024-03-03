package com.example.reviewcompany.domain.repository

import com.example.reviewcompany.data.ArticleEntity
import com.google.firebase.firestore.QuerySnapshot

interface FirebaseRepository {

    suspend fun logIn(email: String, password: String) : Boolean

    suspend fun signUp(email: String, password: String): Boolean

    suspend fun insertArticle(articleEntity: ArticleEntity) : Boolean

    suspend fun getArticle(): QuerySnapshot

    suspend fun deleteArticle(articleEntity: ArticleEntity?) : Boolean


}