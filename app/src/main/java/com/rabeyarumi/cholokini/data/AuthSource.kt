package com.rabeyarumi.cholokini.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.rabeyarumi.cholokini.db.register.User

interface AuthSource {

    fun userRegistration (user: User) : Task<AuthResult>
    fun userLogin (user: User)
    fun userForgetPassword (email: String)
}