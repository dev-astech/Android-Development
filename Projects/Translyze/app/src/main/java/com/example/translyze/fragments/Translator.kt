package com.example.translyze.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.translyze.R
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions

class Translator : Fragment() {

    private lateinit var inputText: EditText
    private lateinit var translateButton: Button
    private lateinit var sourceLanguageSpinner: Spinner
    private lateinit var targetLanguageSpinner: Spinner
    private lateinit var translatedTextView: TextView
    private lateinit var ivCopySourceText : ImageView
    private lateinit var ivCopyTargetText : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_translator, container, false)

        // Initialize views
        inputText = view.findViewById(R.id.editTextInput)
        translateButton = view.findViewById(R.id.buttonTranslate)
        sourceLanguageSpinner = view.findViewById(R.id.spinnerSourceLanguage)
        targetLanguageSpinner = view.findViewById(R.id.spinnerTargetLanguage)
        translatedTextView = view.findViewById(R.id.textViewTranslated)
        ivCopySourceText = view.findViewById(R.id.ivCopySourceText)
        ivCopyTargetText = view.findViewById(R.id.ivCopyTargetText)

        // Language options for both spinners
        val sourceLanguages = listOf("English", "Hindi", "Spanish", "French", "German")
        val targetLanguages = listOf("English", "Hindi", "Spanish", "French", "German")
        val sourceAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, sourceLanguages)
        val targetAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, targetLanguages)
        sourceLanguageSpinner.adapter = sourceAdapter
        targetLanguageSpinner.adapter = targetAdapter

        translateButton.setOnClickListener {
            val text = inputText.text.toString()
            val sourceLang = sourceLanguageSpinner.selectedItem.toString()
            val targetLang = targetLanguageSpinner.selectedItem.toString()

            translateText(text, sourceLang, targetLang)
        }

        ivCopySourceText.setOnClickListener {
            copyToClipboard(inputText.text.toString())
        }

        ivCopyTargetText.setOnClickListener {
            copyToClipboard(translatedTextView.text.toString())
        }

        return view
    }

    private fun copyToClipboard(text: String) {
        if (text.isNotEmpty()) {
            val clipboard: ClipboardManager = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Copied Text", text)
            clipboard.setPrimaryClip(clip)

            // Show a toast message
            Toast.makeText(requireContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }
    }


    private fun translateText(text: String, sourceLang: String, targetLang: String) {
        val sourceLanguageCode = when (sourceLang) {
            "Spanish" -> TranslateLanguage.SPANISH
            "French" -> TranslateLanguage.FRENCH
            "German" -> TranslateLanguage.GERMAN
            "Hindi" -> TranslateLanguage.HINDI
            else -> TranslateLanguage.ENGLISH
        }
        val targetLanguageCode = when (targetLang) {
            "Spanish" -> TranslateLanguage.SPANISH
            "French" -> TranslateLanguage.FRENCH
            "German" -> TranslateLanguage.GERMAN
            "Hindi" -> TranslateLanguage.HINDI
            else -> TranslateLanguage.ENGLISH
        }

        val options = TranslatorOptions.Builder()
            .setSourceLanguage(sourceLanguageCode)
            .setTargetLanguage(targetLanguageCode)
            .build()
        val translator = Translation.getClient(options)

        // Download model if not available
        translator.downloadModelIfNeeded()
            .addOnSuccessListener {
                translator.translate(text)
                    .addOnSuccessListener { translatedText ->
                        translatedTextView.text = translatedText
                    }
                    .addOnFailureListener { e ->
                        translatedTextView.text = "Error: ${e.message}"
                    }
            }
            .addOnFailureListener { e ->
                translatedTextView.text = "Error: ${e.message}"
            }
    }
}
