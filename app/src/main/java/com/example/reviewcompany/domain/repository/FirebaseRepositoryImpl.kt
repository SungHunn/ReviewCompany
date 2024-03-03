package com.example.reviewcompany.domain.repository

import com.example.reviewcompany.data.ArticleEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore,
) : FirebaseRepository {

    override suspend fun logIn(email: String, password: String): Boolean {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            (authResult.user != null)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            (authResult.user != null)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun insertArticle(articleEntity: ArticleEntity): Boolean {
        return try {
            firebaseAuth.currentUser?.let {
                firebaseDb.collection("article")
                    .document("${articleEntity.uid} + ${articleEntity.companyName}")
                    .set(articleEntity).await()
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun getArticle(): QuerySnapshot {
        val dbResult = firebaseAuth.currentUser?.let {
            firebaseDb.collection("article")
                .get()
                .await()
        }
        return dbResult!!
    }

    override suspend fun deleteArticle(articleEntity: ArticleEntity?): Boolean {
        return try {
            val results = getArticle()
            for (result in results) {
                if (result.id == articleEntity?.articleId) {
                    result.reference.delete()
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun editArticle(articleEntity: ArticleEntity?): Boolean {
        return try {
            val results = getArticle()
            for (result in results) {
                if (result.id == articleEntity?.articleId) {
                    firebaseDb.collection("article")
                        .document("${articleEntity.uid} + ${articleEntity.companyName}")
                        .set(articleEntity).await()
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}