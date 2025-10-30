package com.example.musiccreater.UI.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.musiccreater.Base.BaseActivity
import com.example.musiccreater.R
import com.example.musiccreater.databinding.ActivitySplashBinding
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    private val handler = Handler(Looper.getMainLooper())
    private val splashRunnable = Runnable {
        UltimateBarX.statusBarOnly(this).fitWindow(true).colorRes(com.example.musiccreater.R.color.themeColor).light(false)
            .apply()

        window.statusBarColor = ContextCompat.getColor(this, R.color.themeColor)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        startActivity(Intent(this, SignInActivity::class.java))

//        if (SharedPreferences.getBoolean("IS_LOGGED_IN", false)==true) {
//            startActivity(Intent(this, SignInActivity::class.java))
//        }else{
//            startActivity(Intent(this, SignInActivity::class.java))
//        }
//        finish()
    }
    override fun init() {
        handler.postDelayed(splashRunnable, 3500)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(splashRunnable)
    }
}