package com.example.reviewcompany.domain.repository

interface FirebaseRepository {

    suspend fun logIn(email: String, password: String) : Boolean

    suspend fun signUp(email: String, password: String): Boolean

}