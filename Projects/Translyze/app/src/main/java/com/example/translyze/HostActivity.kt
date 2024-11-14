package com.example.translyze

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.translyze.fragments.BarcodeScanner
import com.example.translyze.fragments.ImageLabeler
import com.example.translyze.fragments.TextRecognition
import com.example.translyze.fragments.Translator


class HostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)

        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Assume you receive the fragment selection as an intent extra
        val selectedFragment = intent.getStringExtra("FRAGMENT_SELECTION")

        // Load the appropriate fragment based on the selection
        if (selectedFragment != null) {
            loadFragment(getFragment(selectedFragment))
        } else {
            // Default fragment if nothing is passed
            loadFragment(Translator())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun getFragment(fragmentName: String): Fragment {
        return when (fragmentName) {
            "Translator" -> Translator()
            "BarcodeScanner" -> BarcodeScanner()
            "TextRecognition" -> TextRecognition()
            "ImageLabeler" -> ImageLabeler()
            else -> Translator() // Default fragment
        }
    }
}
