package com.example.reviewcompany.data.remote.datasource

import com.example.reviewcompany.data.model.DataArticle
import com.google.firebase.firestore.QuerySnapshot

interface FirebaseDataSource {

    suspend fun logIn(email: String, password: String) : Boolean

    suspend fun signUp(email: String, password: String): Boolean

    suspend fun insertArticle(dataArticle: DataArticle) : Boolean

    suspend fun getArticle(): QuerySnapshot

    suspend fun deleteArticle(dataArticle: DataArticle?) : Boolean

    suspend fun editArticle(dataArticle: DataArticle?) : Boolean
}