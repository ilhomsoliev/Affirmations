package com.ilhomsoliev.affirmations.presentation.settings

sealed class SettingsEvent {
    object OnBackClick : SettingsEvent()
    object GoToNotifications : SettingsEvent()
    object GoToAddAffirmation : SettingsEvent()
    object GoToWidgets : SettingsEvent()
    object GoToLanguages : SettingsEvent()
    object GoToContentPreferences : SettingsEvent()
    object GoToFavorites : SettingsEvent()

}