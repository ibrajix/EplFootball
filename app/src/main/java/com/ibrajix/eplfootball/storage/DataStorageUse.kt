package com.ibrajix.eplfootball.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_Storage")

@Singleton
class DataStorageUse @Inject constructor(@ApplicationContext context: Context) : DataStorage {

    private val dataStore = context.dataStore

    //keys
    private object PreferenceKeys{
        val HAS_SEEN_INTRO = booleanPreferencesKey("has_seen_intro")
    }

    override fun hasUserSeenIntro() = dataStore.data.catch {
        if (it is IOException){
            emit(emptyPreferences())
        } else throw it
    }.map {
        it[PreferenceKeys.HAS_SEEN_INTRO] ?: false
    }

    override suspend fun setHasSeenIntro(value: Boolean) {
        dataStore.edit {
            it[PreferenceKeys.HAS_SEEN_INTRO]
        }
    }

}