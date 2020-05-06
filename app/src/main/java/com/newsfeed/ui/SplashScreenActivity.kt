package com.newsfeed.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.newsfeed.R

class SplashScreenActivity : AppCompatActivity() {
    private val mWaitHandler = Handler()
    private val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mWaitHandler.postDelayed({
            try {
                startActivity(Intent(context, MainActivity::class.java))
                finish()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, 2300)
    }
}