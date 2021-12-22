package com.ibrajix.eplfootball.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.ibrajix.eplfootball.R

@SuppressLint("CustomSplashScreen")//Not adding android 12's splash screen implementation yet!
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.transparentStatusBar(this)
        ScreenUtils.setFullScreen(this)
        setContentView(R.layout.activity_splash_screen)
    }
}