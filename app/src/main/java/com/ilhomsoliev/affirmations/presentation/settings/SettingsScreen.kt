package com.ilhomsoliev.affirmations.presentation.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ilhomsoliev.affirmations.presentation.components.CustomTopAppBar
import com.ilhomsoliev.affirmations.presentation.settings.components.SettingsListItem

@Composable
fun SettingsScreen(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(text = "Settings", onIconClick = {
            onEvent(SettingsEvent.OnBackClick)
        })
        LazyColumn {
            item {
                SettingsListItem(
                    icon = Icons.Default.Menu,
                    text = "Content Preferences",
                    onClick = {
                        onEvent(SettingsEvent.GoToContentPreferences)

                    })
            }
            item {
                SettingsListItem(
                    icon = Icons.Default.NotificationsActive,
                    text = "Notifications",
                    onClick = {
                        onEvent(SettingsEvent.GoToNotifications)
                    })
            }
            item {
                SettingsListItem(icon = Icons.Default.Language, text = "Language", onClick = {
                    onEvent(SettingsEvent.GoToLanguages)

                })
            }
            item {
                SettingsListItem(icon = Icons.Default.Widgets, text = "Widgets", onClick = {
                    onEvent(SettingsEvent.GoToWidgets)

                })
            }
            item {
                SettingsListItem(icon = Icons.Default.Favorite, text = "Favorites", onClick = {
                    onEvent(SettingsEvent.GoToFavorites)
                })
            }
            item {
                SettingsListItem(
                    icon = Icons.Default.Add,
                    text = "Add your own affirmation",
                    onClick = {
                        onEvent(SettingsEvent.GoToAddAffirmation)
                    })
            }
            item {
                SettingsListItem(icon = Icons.Default.Share, text = "Share", onClick = {
                   // onEvent(SettingsEvent.GoToNotifications)
                })
            }
            item {
                SettingsListItem(icon = Icons.Default.ThumbUp, text = "Rate", onClick = {})
            }
        }
    }


}