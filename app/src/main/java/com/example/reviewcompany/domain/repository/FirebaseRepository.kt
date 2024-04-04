package com.example.reviewcompany.domain.repository

import com.example.reviewcompany.domain.model.DomainArticle
import com.google.firebase.firestore.QuerySnapshot

interface FirebaseRepository {

    suspend fun logIn(email: String, password: String) : Boolean

    suspend fun signUp(email: String, password: String): Boolean

    suspend fun insertArticle(articleEntity: DomainArticle) : Boolean

    suspend fun getArticle(): QuerySnapshot

    suspend fun deleteArticle(articleEntity: DomainArticle?) : Boolean

    suspend fun editArticle(articleEntity: DomainArticle?) : Boolean


}