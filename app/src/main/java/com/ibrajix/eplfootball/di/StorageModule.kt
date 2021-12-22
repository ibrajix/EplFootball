package com.ibrajix.eplfootball.di

import com.ibrajix.eplfootball.storage.DataStorage
import com.ibrajix.eplfootball.storage.DataStorageUse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun bindDataStorage(dataStorageUse: DataStorageUse) : DataStorage

}