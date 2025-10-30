package com.example.musiccreater.UI.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.musiccreater.Model.UiState
import com.example.musiccreater.R
import com.example.musiccreater.UI.ViewModel.LoginResponseModel
import com.example.musiccreater.Utils.SharedPreferences
import com.example.musiccreater.databinding.ActivitySignInBinding
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    private var isShowNewPassword = false
    private var isLoginHandled = false
    private val uLoginViewModel: LoginResponseModel by viewModels()

    companion object {
        private const val RC_SIGN_IN = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UltimateBarX.statusBarOnly(this).fitWindow(true).colorRes(R.color.themeColor).light(false)
            .apply()

        // If already logged in, skip login
        if (SharedPreferences.getBoolean("IS_LOGGED_IN", false)) {
            navigateAfterLogin()
            return
        }

        setupUI()
//        setupGoogleSignIn()
        observeViewModels()
    }

//    private fun setupGoogleSignIn() {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id)) // comes from google-services.json
//            .requestEmail()
//            .build()
//
//        val googleSignInClient = GoogleSignIn.getClient(this, gso)
//
//        binding.LLGoogle.setOnClickListener {
//            // always show account chooser
//            googleSignInClient.signOut().addOnCompleteListener {
//                val signInIntent = googleSignInClient.signInIntent
//                startActivityForResult(
//                    signInIntent,
//                    com.app.ucab.ui.activity.LoginActivity.Companion.RC_SIGN_IN
//                )
//            }
//        }
//    }

    private fun setupUI() {
        binding.icPassword.setOnClickListener { togglePasswordVisibility() }
        binding.ForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        binding.IcRegisterNow.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.icLoginBtn.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = binding.editEmail.text.toString().trim()
        val password = binding.editPassword.text.toString().trim()

        when {
            email.isEmpty() -> {
                binding.editEmail.error = "Email is required"; return
            }

            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.editEmail.error = "Invalid email"; return
            }

            password.isEmpty() -> {
                binding.editPassword.error = "Password is required"; return
            }

            password.length < 6 -> {
                binding.editPassword.error = "Min 6 characters"; return
            }
        }

        showProgress(true)

        // First call USER login API, fallback to DRIVER if it fails
        uLoginViewModel.login(mapOf("email" to email, "password" to password))
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == com.app.ucab.ui.activity.LoginActivity.Companion.RC_SIGN_IN) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                val account = task.getResult(ApiException::class.java)
//                account?.let {
//                    Log.d("GoogleLogin", "--------------------")
//                    Log.d("GoogleLogin", "ID: ${account.id}")
//                    Log.d("GoogleLogin", "Email: ${account.email}")
//                    Log.d("GoogleLogin", "DisplayName: ${account.displayName}")
//                    Log.d("GoogleLogin", "GivenName: ${account.givenName}")
//                    Log.d("GoogleLogin", "FamilyName: ${account.familyName}")
//                    Log.d("GoogleLogin", "PhotoUrl: ${account.photoUrl}")
//                    Log.d("GoogleLogin", "IdToken: ${account.idToken}")
//                    Log.d("GoogleLogin", "--------------------")
//                    SharedPreferences.putString("googleName", account.displayName)
//                    SharedPreferences.putString("googleEmail", account.email)
//                    SharedPreferences.putString("googlePhoto", account.photoUrl?.toString())
//                    // Here send data to your backend (like your driver/user login API)
//                    val creds = mapOf(
//                        "email" to account.email.toString(),
//                        "uid" to account.id.toString(),
//                        "userName" to account.displayName.toString(),
//                        "fullName" to account.givenName.toString(),
//                        "photo" to account.photoUrl.toString(),
//                        "role" to "passenger"
//                    )
//                    // call API via ViewModel (make new API for googleLogin)
//                    uLoginViewModel.googleLogin(creds)
//                }
//            } catch (e: ApiException) {
//                Log.e(
//                    "GoogleSignIn",
//                    "signInResult: failed code=${e.statusCode}, message=${e.message}"
//                )
//                Toast.makeText(this, "Google sign-in failed: ${e.statusCode}", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//    }

    private fun observeViewModels() {
        uLoginViewModel.data.observe(this) { state ->
            when (state) {
                is UiState.Loading -> showProgress(true)

                is UiState.Success -> {
                    showProgress(false)
                    val response = state.data
                    val token = response.token.orEmpty()
                    val id = response.user?.id.orEmpty()
                    val firstName = response.user?.firstName.orEmpty()

                    // Save login info
                    SharedPreferences.putString("token", token)
                    SharedPreferences.putString("userId", id)
                    SharedPreferences.putString("userName", firstName)
                    SharedPreferences.putBoolean("IS_LOGGED_IN", true)

                    Toast.makeText(this, "Welcome ${firstName}", Toast.LENGTH_SHORT).show()
                    Log.d("LOGIN_SUCCESS", "User ID: $id, Token: $token")

                    navigateAfterLogin()
                }

                is UiState.Error -> {
                    showProgress(false)
                    showError(state.message)
                    Log.e("LOGIN_ERROR", state.message)
                }

                else -> Unit
            }
        }
    }


    private fun saveLogin(role: String, token: String, id: String) {
        SharedPreferences.putString("role", role)
        SharedPreferences.putString("token", token)

        if (role == "driver") {
            SharedPreferences.putString("driverId", id)
        } else {
            SharedPreferences.putString("userId", id)
        }

        SharedPreferences.putBoolean("IS_LOGGED_IN", true)
        Log.d("LOGIN_PREFS", "Saved -> role=$role token=$token id=$id")
    }

    private fun navigateAfterLogin() {
        showProgress(false)
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun showError(msg: String) {
        showProgress(false)
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        Log.d("LoginActivity", "showError: $msg")
    }

    private fun showProgress(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        binding.txtLogin.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun togglePasswordVisibility() {
        isShowNewPassword = !isShowNewPassword
        binding.editPassword.transformationMethod =
            if (isShowNewPassword) null else android.text.method.PasswordTransformationMethod.getInstance()
        binding.icPassword.setImageResource(
            if (isShowNewPassword) R.drawable.ic_eye_show else R.drawable.ic_eye_hide
        )
        binding.editPassword.setSelection(binding.editPassword.text?.length ?: 0)
    }

}