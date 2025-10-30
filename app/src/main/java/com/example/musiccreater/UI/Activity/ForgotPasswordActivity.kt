package com.example.musiccreater.UI.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.musiccreater.R
import com.example.musiccreater.databinding.ActivityForgotPasswordBinding
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UltimateBarX.statusBarOnly(this).fitWindow(true).colorRes(R.color.themeColor).light(false)
            .apply()

        setupUI()
    }

    private fun setupUI() {
        binding.icToolbar.ivBack.setOnClickListener({ v -> onBackPressed() })

        binding.icSendCode.setOnClickListener {
//            startActivity(Intent(this, VerifyOTPActivity::class.java))
        }
    }
}