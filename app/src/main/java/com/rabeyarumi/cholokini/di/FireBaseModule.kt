package com.rabeyarumi.cholokini.di


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rabeyarumi.cholokini.data.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FireBaseModule {


    @Provides
    @Singleton
    fun providesFireBaseAuth(): FirebaseAuth{

        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesFireBaseFirestoreDB(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()

    }

    @Provides
    @Singleton
    fun providesFireBase(mAuth:FirebaseAuth, db:FirebaseFirestore): AuthRepository {

        return AuthRepository(mAuth,db)

    }




}