package com.rabeyarumi.cholokini.db.login

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.rabeyarumi.cholokini.core.DataState
import com.rabeyarumi.cholokini.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authService:AuthRepository): ViewModel() {
    private val userResponse = MutableLiveData<DataState<LoginUser>>()
    val _userResponse :LiveData<DataState<LoginUser>> = userResponse

    fun userLogin(user: LoginUser){
        userResponse.postValue(DataState.Loading())

//        val authService = AuthService(mAuth)

        authService.userLogin(user).addOnSuccessListener {
            userResponse.postValue(DataState.Success(user))
        }.addOnFailureListener {
            userResponse.postValue(DataState.Error(it.message))
        }
    }
}