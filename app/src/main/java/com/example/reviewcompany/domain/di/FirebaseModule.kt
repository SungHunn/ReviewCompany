package com.example.reviewcompany.domain.di

import com.example.reviewcompany.domain.repository.FirebaseRepository
import com.example.reviewcompany.domain.repository.FirebaseRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseAuth: FirebaseAuth): FirebaseRepository {
        return FirebaseRepositoryImpl(firebaseAuth = firebaseAuth)
    }
}