package com.ibrajix.eplfootball.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.ibrajix.eplfootball.R
import com.ibrajix.eplfootball.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")//Not adding android 12's splash screen implementation yet!
class SplashScreenActivity : AppCompatActivity() {

    //initialize binding variable
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //assign binding variable
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        //utility function for transparent status bar
        BarUtils.transparentStatusBar(this)

        //utility function for making activity full screen
        ScreenUtils.setFullScreen(this)

        //set view content with binding
        setContentView(binding.root)

        //initializing codes
        initView()

    }

    private fun initView(){
       //check if first time visit, if first time, go to into activity, else go to container activity
       checkIfFirstTimeOpeningApp()
    }

    private fun checkIfFirstTimeOpeningApp(){

    }

}