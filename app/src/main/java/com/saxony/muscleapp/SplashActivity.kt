package com.saxony.muscleapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import android.view.animation.AnimationUtils

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)

        val imgProfile = findViewById<ImageView>(R.id.img_logo)
        val imgLogo = findViewById<ImageView>(R.id.img_logo)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        imgLogo.startAnimation(fadeIn)

        Glide.with(this)
            .load(R.drawable.icon_muscle2)
            .apply(RequestOptions.circleCropTransform())
            .into(imgProfile)
    }
}