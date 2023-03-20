package com.ilhomsoliev.affirmations.presentation.contentPreferences

import com.ilhomsoliev.affirmations.presentation.favorties.FavoritesEvent

sealed class ContentPreferencesEvent {
    object OnBackClick : ContentPreferencesEvent()
    object OnDismissDialog : ContentPreferencesEvent()
    data class OnDarkPsychologyClick(val value: Boolean) : ContentPreferencesEvent()
}