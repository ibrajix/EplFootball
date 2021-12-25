/*
 * Written and Developed by Inuwa Ibrahim - https://linktr.ee/Ibrajix
 * All rights reserved
 */

package com.ibrajix.eplfootball.ui.viewmodel

import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ibrajix.eplfootball.storage.DataStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStorageViewModel @Inject constructor(private val dataStorage: DataStorage): ViewModel() {

    //extension function for converting flow to livedata
    var hasSeenIntro = dataStorage.hasUserSeenIntro().asLiveData()

    //function for updating the boolean value if user has seen intro
    fun setHasSeenIntro(value: Boolean){
       viewModelScope.launch {
           dataStorage.setHasSeenIntro(value)
       }
    }

}