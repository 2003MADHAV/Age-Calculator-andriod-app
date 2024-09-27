package com.example.agecalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // This method should be defined if you want edge-to-edge effects
        setContentView(R.layout.activity_splash_screen)



        // Set up window insets for edge-to-edge display (optional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set a timer for the splash screen (3 seconds)
        Handler(Looper.getMainLooper()).postDelayed({
            // After 3 seconds, start the MainActivity
            val intent = Intent(this, MainActivity::class.java) // Change MainActivity to your actual main activity
            startActivity(intent)
            finish() // Close the splash screen activity
        }, 3000) // Delay of 3000 milliseconds (3 seconds)
    }
}
