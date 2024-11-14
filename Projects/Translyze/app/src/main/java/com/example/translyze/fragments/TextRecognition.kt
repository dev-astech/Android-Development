package com.example.translyze.fragments

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.translyze.R
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TextRecognition : Fragment() {

    private lateinit var ivCamera: ImageView
    private lateinit var btnCaptureImage: Button
    private lateinit var tvResult: TextView
    private lateinit var btnCopyText: Button

    private var currentPhotoPath: String? = null
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_text_recognition, container, false)

        ivCamera = view.findViewById(R.id.imageViewCamera)
        btnCaptureImage = view.findViewById(R.id.btnCaptureImage)
        tvResult = view.findViewById(R.id.textViewResult)
        btnCopyText = view.findViewById(R.id.buttonCopyText)

        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    captureImage()
                } else {
                    Toast.makeText(requireContext(), "Camera Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }

        takePictureLauncher =
            registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
                if (isSuccess) {
                    currentPhotoPath?.let { path ->
                        val bitmap = BitmapFactory.decodeFile(path)
                        ivCamera.setImageBitmap(bitmap)
                        recognizeText(bitmap)
                    }
                }
            }

        btnCaptureImage.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }

        return view
    }

    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun captureImage() {
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            Toast.makeText(requireContext(), "Error occurred while creating the file", Toast.LENGTH_SHORT)
                .show()
            null
        }
        photoFile?.also {
            val photoUri: Uri = FileProvider.getUriForFile(
                requireContext(),
                "${requireContext().packageName}.provider",
                it
            )
            takePictureLauncher.launch(photoUri)
        }
    }

    private fun recognizeText(bitmap: Bitmap) {
        val image = InputImage.fromBitmap(bitmap, 0)
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        recognizer.process(image)
            .addOnSuccessListener { ocrText ->
                tvResult.text = ocrText.text
                tvResult.movementMethod = ScrollingMovementMethod()
                btnCopyText.visibility = Button.VISIBLE
                btnCopyText.setOnClickListener {
                    val clipboard = ContextCompat.getSystemService(requireContext(), android.content.ClipboardManager::class.java)
                    val clip = android.content.ClipData.newPlainText("recognized text", ocrText.text)
                    clipboard?.setPrimaryClip(clip)
                    Toast.makeText(requireContext(), "Text Copied", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(requireContext(), "Failed to recognize text: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }
}
