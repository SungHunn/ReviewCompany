package com.example.reviewcompany.data.remote.datasourceimpl

import com.example.reviewcompany.data.model.DataArticle
import com.example.reviewcompany.data.remote.datasource.FirebaseDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore,
) : FirebaseDataSource {

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

    override suspend fun insertArticle(dataArticle: DataArticle): Boolean {
        return try {
            firebaseAuth.currentUser?.let {
                firebaseDb.collection("article")
                    .document("${dataArticle.uid} + ${dataArticle.companyName}")
                    .set(dataArticle).await()
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

    override suspend fun deleteArticle(dataArticle: DataArticle?): Boolean {
        return try {
            val results = getArticle()
            for (result in results) {
                if (result.id == dataArticle?.articleId) {
                    result.reference.delete()
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun editArticle(dataArticle: DataArticle?): Boolean {
        return try {
            val results = getArticle()
            for (result in results) {
                if (result.id == dataArticle?.articleId) {
                    firebaseDb.collection("article")
                        .document("${dataArticle.uid} + ${dataArticle.companyName}")
                        .set(dataArticle).await()
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}