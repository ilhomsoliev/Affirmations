package com.ilhomsoliev.affirmations.presentation.notifications

import com.ilhomsoliev.affirmations.presentation.settings.SettingsEvent

sealed class NotificationsEvent {
    object OnBackClick : NotificationsEvent()
    object OnSoundChooseClick : NotificationsEvent()
    object IncreaseRepetition : NotificationsEvent()
    object IncreaseStartTime : NotificationsEvent()
    object IncreaseEndTime : NotificationsEvent()
    object DecreaseRepetition : NotificationsEvent()
    object DecreaseStartTime : NotificationsEvent()
    object DecreaseEndTime : NotificationsEvent()
    data class OnIsNotificationValueChange(val value:Boolean):NotificationsEvent()

}