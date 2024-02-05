package com.rabeyarumi.cholokini

import android.widget.EditText

fun EditText.isEmpty():Boolean {

    if (this.text.toString().isEmpty()){
        this.error = "This Filed Need to be Fill up !"
        return true
    }

    return false
}









