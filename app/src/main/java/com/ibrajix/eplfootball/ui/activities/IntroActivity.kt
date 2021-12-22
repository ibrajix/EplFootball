package com.ibrajix.eplfootball.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.ibrajix.eplfootball.R
import com.ibrajix.eplfootball.databinding.ActivityIntroBinding
import com.ibrajix.eplfootball.utils.Constants.IMAGE_URL

class IntroActivity : AppCompatActivity() {

    //initialize binding variable
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //assign binding variable
        binding = ActivityIntroBinding.inflate(layoutInflater)

        //set content to view via binding
        setContentView(binding.root)

        //load my own code
        initView()

    }

    private fun initView(){
        //display epl image with coil
        binding.imageView.load(IMAGE_URL){
            placeholder(R.drawable.ic_launcher_logo)
        }
    }

}