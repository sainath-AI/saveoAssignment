package com.masai.sainath.saveoproject.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.masai.sainath.saveoproject.R

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val intent = Intent(this, MovieListActivity::class.java)
            startActivity(intent)
        }, 3500)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}