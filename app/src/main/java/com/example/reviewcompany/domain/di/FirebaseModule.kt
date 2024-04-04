package com.example.reviewcompany.domain.di

import com.example.reviewcompany.data.remote.datasource.FirebaseDataSource
import com.example.reviewcompany.domain.repository.FirebaseRepository
import com.example.reviewcompany.data.repository.FirebaseRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideFireStoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseDataSource: FirebaseDataSource): FirebaseRepository {
        return FirebaseRepositoryImpl(firebaseDataSource)
    }
}