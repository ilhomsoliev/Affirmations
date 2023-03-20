package com.ilhomsoliev.affirmations.presentation.notifications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.affirmations.presentation.components.CustomTopAppBar
import com.ilhomsoliev.affirmations.presentation.contentPreferences.ContentPreferencesEvent
import com.ilhomsoliev.affirmations.presentation.notifications.components.TextInDe
import com.ilhomsoliev.affirmations.presentation.notifications.components.WeekDays

@Composable
fun NotificationsScreen(
    state: NotificationsState,
    onEvent: (NotificationsEvent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(text = "Notifications", onIconClick = {
            onEvent(NotificationsEvent.OnBackClick)
        })
        LazyColumn(
            modifier = Modifier.padding(12.dp),
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Do you notification with affirmation every day:")
                    Switch(
                        checked = state.isNotificationOn,
                        onCheckedChange = {
                            onEvent(NotificationsEvent.OnIsNotificationValueChange(it))
                        },
                    )
                }
            }
            if (state.isNotificationOn) {
                item {
                    TextInDe(
                        text = "Start at",
                        value = "${state.startTime}:00",
                        onIncreaseClick = {
                            onEvent(NotificationsEvent.IncreaseStartTime)

                        },
                        onDecreaseClick = {
                            onEvent(NotificationsEvent.DecreaseStartTime)
                        },
                        isIncreaseButtonActive = state.endTime - 1 == state.startTime,
                        isDecreaseButtonActive = state.startTime == 1
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                }

                /*
                item {

                    TextInDe(
                        text = "How many",
                        value = "${state.repetitions}x",
                        onIncreaseClick = {
                            onEvent(NotificationsEvent.IncreaseRepetition)

                        },
                        onDecreaseClick = {
                            onEvent(NotificationsEvent.DecreaseRepetition)

                        },
                        isIncreaseButtonActive = state.repetitions == 10,
                        isDecreaseButtonActive = state.repetitions == 1
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
                item {

                    TextInDe(
                        text = "End at",
                        value = "${state.endTime}:00",
                        onIncreaseClick = {
                            onEvent(NotificationsEvent.IncreaseEndTime)
                        },
                        onDecreaseClick = {
                            onEvent(NotificationsEvent.DecreaseEndTime)
                        },
                        isDecreaseButtonActive = (state.endTime - 1 == state.startTime),
                        isIncreaseButtonActive = state.endTime == 24
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                }*/
                /*item {
                    WeekDays()
                    Spacer(modifier = Modifier.height(12.dp))
                }*/
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Sound")

                        Row(modifier = Modifier.clickable {
                            onEvent(NotificationsEvent.OnSoundChooseClick)
                        }, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "System Defaults")
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = null
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                }
            }
        }
    }
}