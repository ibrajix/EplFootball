package com.ibrajix.eplfootball.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import coil.load
import com.ibrajix.eplfootball.R
import com.ibrajix.eplfootball.databinding.ActivityIntroBinding
import com.ibrajix.eplfootball.ui.viewmodel.DataStorageViewModel
import com.ibrajix.eplfootball.utils.Constants.IMAGE_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : AppCompatActivity() {

    //initialize binding variable
    private lateinit var binding: ActivityIntroBinding

    //initialize storageViewModel
    private val dataStorageViewModel: DataStorageViewModel by viewModels()

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

        //display epl image using coil
        binding.imageView.load(IMAGE_URL){
            placeholder(R.drawable.ic_launcher_logo)
        }

        //on click of any item in view
        handleClicks()

    }

    private fun handleClicks(){

        //on click button
        binding.btnEnter.setOnClickListener {

            //go to container activity
            val intent = Intent(this, ContainerActivity::class.java)
            startActivity(intent)

        }

    }

}