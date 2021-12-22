package com.ibrajix.eplfootball.storage

import kotlinx.coroutines.flow.Flow

interface DataStorage {

    //check if user has seen intro
    fun hasUserSeenIntro() : Flow<Boolean>

    //set user intro boolean to true or false, (seen or not seen)
    suspend fun setHasSeenIntro(value: Boolean)

}