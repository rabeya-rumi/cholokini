package com.rabeyarumi.cholokini.db.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.rabeyarumi.cholokini.core.DataState
import com.rabeyarumi.cholokini.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authService: AuthRepository): ViewModel() {

    private val _registrationResponse = MutableLiveData<DataState<User>>()
    val registrationResponse : LiveData<DataState<User>> = _registrationResponse

    fun userRegistration(user: User) {
        _registrationResponse.postValue(DataState.Loading())
//        val authService = AuthService(mAuth)

        authService.userRegistration(user)
            .addOnSuccessListener {

                it.user?.let {createdUser ->

                    user.userId = createdUser.uid

                    authService.createUser(user).addOnSuccessListener {
                        _registrationResponse.postValue(DataState.Success(user))
                        //Log.d("TAG", "userRegistration: Success")
                    }.addOnFailureListener {
                        _registrationResponse.postValue(DataState.Error(it.message))
                    }
                }


            } .addOnFailureListener {
                _registrationResponse.postValue(DataState.Error(it.message))
                    //Log.d("TAG", "userRegistration: ${it.message}")
            }
    }


}