package com.ilhomsoliev.affirmations.presentation.notifications

data class NotificationsState(
    val repetitions: Int = 10,
    val startTime: Int = 1,
    val endTime: Int = 1,
    val isNotificationOn: Boolean = false,
)