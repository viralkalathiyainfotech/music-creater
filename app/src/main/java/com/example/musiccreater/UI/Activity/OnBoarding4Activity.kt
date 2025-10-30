package com.example.musiccreater.UI.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.musiccreater.R
import com.example.musiccreater.databinding.ActivityOnBoarding4Binding

class OnBoarding4Activity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoarding4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoarding4Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}