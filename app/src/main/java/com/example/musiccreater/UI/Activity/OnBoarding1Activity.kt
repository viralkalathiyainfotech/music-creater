package com.example.musiccreater.UI.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.musiccreater.R
import com.example.musiccreater.databinding.ActivityOnBoarding1Binding

class OnBoarding1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoarding1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoarding1Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}