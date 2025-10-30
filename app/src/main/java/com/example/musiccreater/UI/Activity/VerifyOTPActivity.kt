package com.example.musiccreater.UI.Activity

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.musiccreater.Helper.CustomTypefaceSpan
import com.example.musiccreater.R
import com.example.musiccreater.databinding.ActivityVerifyOtpactivityBinding
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX

class VerifyOTPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVerifyOtpactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UltimateBarX.statusBarOnly(this).fitWindow(true).colorRes(R.color.themeColor).light(false)
            .apply()

        setupEmailText("example123@gmail.com")
        setupResendText()
        setUi()

    }

    private fun setUi() {
        binding.icToolbar.ivBack.setOnClickListener({ v -> onBackPressed() })
    }

    private fun setupEmailText(email: String) {
        val text = "We’ve sent a code to $email\nPlease enter it to verify your Email."
        val spannable = SpannableString(text)

        // Highlight email in white
        val start = text.indexOf(email)
        val end = start + email.length
        spannable.setSpan(
            ForegroundColorSpan(Color.WHITE),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        // Underline the email
        spannable.setSpan(
            UnderlineSpan(),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        //  Apply Inter Medium font
        val typeface = ResourcesCompat.getFont(this, R.font.inter_medium)
        spannable.setSpan(
            CustomTypefaceSpan(typeface!!),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        binding.tvName.text = spannable
    }

    private fun setupResendText() {
        val text = "Didn’t receive code? Resend"
        val spannable = SpannableString(text)
        val start = text.indexOf("Resend")
        val end = text.length
        spannable.setSpan(
            ForegroundColorSpan(Color.WHITE),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}