package com.ilhomsoliev.affirmations.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences_name")
        val NOTIFICATIONS_REPETITIONS_KEY = intPreferencesKey("notifications_repetitions")
        val IS_DARK_PSYCHOLOGY_ACTIVE_KEY = booleanPreferencesKey("IS_DARK_PSYCHOLOGY_ACTIVE_KEY")
        val START_AT_NOTIFICATION_KEY = intPreferencesKey("start_at_notification")
        val END_AT_NOTIFICATION_KEY = intPreferencesKey("end_at_notification")
        val CATEGORY_ID_KEY = intPreferencesKey("CATEGORY_ID_KEY")
        val LANGUAGE_ID_KEY = intPreferencesKey("LANGUAGE_ID_KEY")
        val IS_NOTIFICATION_ON_KEY = booleanPreferencesKey("IS_NOTIFICATION_ON_KEY")
    }

    suspend fun updateNotificationsRepetition(times: Int) {
        context.dataStore.edit { preferences ->
            preferences[NOTIFICATIONS_REPETITIONS_KEY] = times
        }
    }

    suspend fun updateIsDarkPsychologyActive(times: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK_PSYCHOLOGY_ACTIVE_KEY] = times
        }
    }

    suspend fun updateStartAtNotification(date: Int) {
        context.dataStore.edit { preferences ->
            preferences[START_AT_NOTIFICATION_KEY] = date
        }
    }

    suspend fun updateEndAtNotification(date: Int) {
        context.dataStore.edit { preferences ->
            preferences[END_AT_NOTIFICATION_KEY] = date
        }
    }

    suspend fun updateCategoryId(id: Int) {
        context.dataStore.edit { preferences ->
            preferences[CATEGORY_ID_KEY] = id
        }
    }

    suspend fun updateLanguage(id: Int) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_ID_KEY] = id
        }
    }

    suspend fun updateIsNotificationOn(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_NOTIFICATION_ON_KEY] = value
        }
    }

    val getNotificationRepetition = context.dataStore.data.map {
        it[NOTIFICATIONS_REPETITIONS_KEY] ?: 5
    }
    val getStartAtNotification = context.dataStore.data.map {
        it[START_AT_NOTIFICATION_KEY] ?: 10
    }
    val getEndAtNotification = context.dataStore.data.map {
        it[END_AT_NOTIFICATION_KEY] ?: 20
    }
    val getIsDarkPsychology = context.dataStore.data.map {
        it[IS_DARK_PSYCHOLOGY_ACTIVE_KEY] ?: false
    }
    val getCategoryId = context.dataStore.data.map {
        it[CATEGORY_ID_KEY] ?: 3
    }
    val getLanguageId = context.dataStore.data.map {
        it[LANGUAGE_ID_KEY] ?: 3
    }
    val getIsNotificationOn = context.dataStore.data.map {
        it[IS_NOTIFICATION_ON_KEY] ?: false
    }
}