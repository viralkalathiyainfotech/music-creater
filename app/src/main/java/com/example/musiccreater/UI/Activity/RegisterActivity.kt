package com.example.musiccreater.UI.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.musiccreater.R
import com.example.musiccreater.databinding.ActivityRegisterBinding
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX
import kotlin.getValue
import kotlin.toString

class RegisterActivity : AppCompatActivity() {
    private var isShowNewPassword = false

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UltimateBarX.statusBarOnly(this).fitWindow(true).colorRes(R.color.themeColor).light(false)
            .apply()

        setUi()
    }

    private fun setUi() {
        binding.icToolbar.ivBack.setOnClickListener({ v -> onBackPressed() })
        binding.IcSigninNow.setOnClickListener({ v ->
            startActivity(Intent(this, SignInActivity::class.java))
        })
        binding.icRegisterBtn.setOnClickListener { view ->
            registerUser();
        }
        binding.icPassword.setOnClickListener { view ->
            hideShowNewPassword();
        }
        observeViewModel()
    }

    private fun registerUser() {
        val username = binding.editUsername.text.toString().trim()
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPassword.text.toString().trim()
        val phoneNumber = binding.editLatName.text.toString().trim()

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        registerViewModel.registerUserOrDriver(username, email, password, phoneNumber, isUser)
    }

    private fun hideShowNewPassword() {
        isShowNewPassword = !isShowNewPassword
        binding.apply {
            editPassword.transformationMethod =
                if (isShowNewPassword) null else android.text.method.PasswordTransformationMethod.getInstance()

            icPassword.setImageResource(
                if (isShowNewPassword) R.drawable.ic_eye_show else R.drawable.ic_eye_hide
            )
            editPassword.setSelection(editPassword.text?.length ?: 0)
        }
    }

}