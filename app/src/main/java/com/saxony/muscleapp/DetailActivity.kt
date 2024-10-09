package com.saxony.muscleapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Inisialisasi view
        val imgMuscleDetail: ImageView = findViewById(R.id.img_muscle_detail)
        val tvNameDetail: TextView = findViewById(R.id.tv_name_detail)
        val tvDescriptionDetail: TextView = findViewById(R.id.tv_description_detail)
        val shareButton: Button = findViewById(R.id.action_share)

        // Ambil data yang dikirim dari halaman utama
        val muscle = intent.getParcelableExtra<Muscle>("extra_muscle")

        // Set data pada layout
        if (muscle != null) {
            imgMuscleDetail.setImageResource(muscle.photo)
            tvNameDetail.text = muscle.name
            tvDescriptionDetail.text = muscle.description
        }

        // Set listener untuk tombol share
        shareButton.setOnClickListener {
            shareMuscleDetail(muscle)
        }
    }

    private fun shareMuscleDetail(muscle: Muscle?) {
        // Memastikan bahwa objek muscle tidak null
        muscle?.let {
            // Membuat string yang akan dibagikan
            val shareText = "Check out this muscle!\nName: ${it.name}\nDescription: ${it.description}"

            // Membuat Intent untuk berbagi
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }

            // Memulai Activity untuk berbagi
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}
