package com.example.translyze

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var cvTranslator : CardView
    private lateinit var cvImageLabeler : CardView
    private lateinit var cvBarcodeScanner : CardView
    private lateinit var cvTextRecognition : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        cvTranslator = findViewById(R.id.cvTranslator)
        cvImageLabeler = findViewById(R.id.cvImageLabeler)
        cvBarcodeScanner = findViewById(R.id.cvBarcodeScanner)
        cvTextRecognition = findViewById(R.id.cvTextRecognition)

        val intent = Intent(this, HostActivity::class.java)

        cvTranslator.setOnClickListener {
            intent.putExtra("FRAGMENT_SELECTION", "Translator")
            startActivity(intent)
        }

        cvImageLabeler.setOnClickListener {
            intent.putExtra("FRAGMENT_SELECTION", "ImageLabeler")
            startActivity(intent)
            Log.d("Image Labeler", "hello")
        }

        cvBarcodeScanner.setOnClickListener {
            intent.putExtra("FRAGMENT_SELECTION", "BarcodeScanner")
            startActivity(intent)
        }

        cvTextRecognition.setOnClickListener {
            intent.putExtra("FRAGMENT_SELECTION", "TextRecognition")
            startActivity(intent)
        }

    }
}