
package com.example.musiccreater.UI.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.musiccreater.R
import com.example.musiccreater.databinding.ActivityResetPasswordBinding
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX

class ResetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UltimateBarX.statusBarOnly(this).fitWindow(true).colorRes(R.color.themeColor).light(false)
            .apply()
        setUi()

    }
    private fun setUi() {
        binding.icToolbar.ivBack.setOnClickListener({ v -> onBackPressed() })
    }
}