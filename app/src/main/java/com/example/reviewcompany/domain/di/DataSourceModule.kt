package com.example.reviewcompany.domain.di

import com.example.reviewcompany.data.remote.datasource.FirebaseDataSource
import com.example.reviewcompany.data.remote.datasourceimpl.FirebaseDataSourceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    @Singleton
    fun provideMainDataSource(
        firebaseAuth: FirebaseAuth,
        firebaseDb: FirebaseFirestore
    ) : FirebaseDataSource {
        return FirebaseDataSourceImpl(firebaseAuth, firebaseDb)
    }
}