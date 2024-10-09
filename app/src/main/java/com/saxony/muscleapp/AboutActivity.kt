package com.saxony.muscleapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class AboutActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name = findViewById<TextView>(R.id.tv_name)
        val email = findViewById<TextView>(R.id.tv_email)
        val imgProfile = findViewById<ImageView>(R.id.img_profile)

        Glide.with(this)
            .load(R.drawable.profile)
            .apply(RequestOptions.circleCropTransform())
            .into(imgProfile)

        name.text = "I Komang Indra Pramana" //
        email.text = "komangindra.p2104@gmail.com"
    }
}
