package com.rabeyarumi.cholokini

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rabeyarumi.cholokini.db.starter.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {


    private val splashTimeOut: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({

            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()

        }, splashTimeOut)
    }
}