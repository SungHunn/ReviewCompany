package com.example.reviewcompany.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    //private val firebaseDb: FirebaseFirestore
): FirebaseRepository{

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

//            firebaseAuth.currentUser?.let {
//                firebaseDb.collection("user")
//                    .document(it.uid)
//                    .set(MyInfo(it.uid, nickname))
//            }

            (authResult.user != null)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}