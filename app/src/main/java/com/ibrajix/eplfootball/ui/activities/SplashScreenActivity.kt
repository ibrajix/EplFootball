package com.ibrajix.eplfootball.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.ibrajix.eplfootball.R
import com.ibrajix.eplfootball.databinding.ActivitySplashScreenBinding
import com.ibrajix.eplfootball.ui.viewmodel.DataStorageViewModel
import com.ibrajix.eplfootball.utils.Constants.SPLASH_SCREEN_TIME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")//Not adding android 12's splash screen implementation yet!
class SplashScreenActivity : AppCompatActivity() {

    //initialize binding variable
    private lateinit var binding: ActivitySplashScreenBinding

    //initialize intent
    private var activityIntent: Intent? = null

    //initialize storage viewModel
    private val datStorageViewModel: DataStorageViewModel by viewModels()

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
       handlePersistence()

    }

    private fun handlePersistence(){

        //observe live data --> if user has seen intro
        datStorageViewModel.hasSeenIntro.observe(this){
            //if user has seen intro (go to container activity), if not, go to (into activity)_....
            activityIntent = if (!it){
                Intent(this, ContainerActivity::class.java)
            } else Intent(this, IntroActivity::class.java)
        }

        //delay for 3 seconds just to show the splash screen (bouncing ball more :)), Not the ideal way of implementing splash screen but who cares?
        //I know, I could use coroutines delay() but no much difference in this context
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(activityIntent)
            finish()}, SPLASH_SCREEN_TIME)

    }

}