package com.example.simpleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var editText: EditText
    private val images = arrayOf(
        R.drawable.image1, // Replace these with your actual drawable resource IDs
        R.drawable.image2,
        R.drawable.image3
    )
    private var currentImageResId: Int = images[0] // Default to the first image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        editText = findViewById(R.id.EditText)
        val button: Button = findViewById(R.id.button)

        // Load saved state
        val sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        currentImageResId = sharedPreferences.getInt("imageResId", currentImageResId)
        imageView.setImageResource(currentImageResId)
        editText.setText(sharedPreferences.getString("editTextContent", ""))

        button.setOnClickListener {
            // Load a random image
            currentImageResId = images.random()
            imageView.setImageResource(currentImageResId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Save current state
        val sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt("imageResId", currentImageResId)
            putString("editTextContent", editText.text.toString())
            apply()
        }
    }
}
