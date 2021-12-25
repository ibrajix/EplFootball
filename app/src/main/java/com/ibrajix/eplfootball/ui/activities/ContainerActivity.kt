/*
 * Written and Developed by Inuwa Ibrahim - https://linktr.ee/Ibrajix
 * All rights reserved
 */

package com.ibrajix.eplfootball.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ibrajix.eplfootball.R
import com.ibrajix.eplfootball.ui.viewmodel.DataStorageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainerActivity : AppCompatActivity() {

    //initialize data storage viewModel
    private val dataStorageViewModel: DataStorageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        //If the user made it to this activity, user has seen into, set value to true
        dataStorageViewModel.setHasSeenIntro(true)

    }
}