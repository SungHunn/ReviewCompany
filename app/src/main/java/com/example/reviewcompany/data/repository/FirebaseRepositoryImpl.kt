package com.example.reviewcompany.data.repository

import com.example.reviewcompany.data.mapper.Mapper
import com.example.reviewcompany.data.remote.datasource.FirebaseDataSource
import com.example.reviewcompany.domain.model.DomainArticle
import com.example.reviewcompany.domain.repository.FirebaseRepository
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseDataSource: FirebaseDataSource
) : FirebaseRepository {

    override suspend fun logIn(email: String, password: String): Boolean {
       return firebaseDataSource.logIn(email, password)
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        return firebaseDataSource.signUp(email, password)
    }

    override suspend fun insertArticle(domainArticle: DomainArticle): Boolean {
        return firebaseDataSource.insertArticle(Mapper.articleMapper(domainArticle))
    }

    override suspend fun getArticle(): QuerySnapshot {
        return firebaseDataSource.getArticle()
    }

    override suspend fun deleteArticle(domainArticle: DomainArticle?): Boolean {
        return firebaseDataSource.deleteArticle(domainArticle?.let { Mapper.articleMapper(it) })
    }

    override suspend fun editArticle(domainArticle: DomainArticle?): Boolean {
        return firebaseDataSource.editArticle(domainArticle?.let { Mapper.articleMapper(it) })
    }
}