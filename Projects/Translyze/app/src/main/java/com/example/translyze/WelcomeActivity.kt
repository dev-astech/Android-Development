package com.example.translyze

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

@Suppress("DEPRECATION")
class WelcomeActivity : AppCompatActivity() {

    private lateinit var btnGetStarted : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        btnGetStarted = findViewById(R.id.btnGetStarted)

        btnGetStarted.setOnClickListener{
            val intent =  Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}