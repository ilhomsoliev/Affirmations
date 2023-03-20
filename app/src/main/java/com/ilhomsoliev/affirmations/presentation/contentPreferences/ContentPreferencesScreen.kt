package com.ilhomsoliev.affirmations.presentation.contentPreferences

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.affirmations.presentation.components.CustomTopAppBar
import com.ilhomsoliev.affirmations.presentation.favorties.FavoritesEvent

@Composable
fun ContentPreferencesScreen(
    state: ContentPreferencesState,
    onEvent: (ContentPreferencesEvent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CustomTopAppBar(text = "Content Preferences", onIconClick = {
            onEvent(ContentPreferencesEvent.OnBackClick)
        })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Do you want to use Dark Psychology:")
            Switch(
                checked = state.isDarkPsychology,
                onCheckedChange = {
                    onEvent(ContentPreferencesEvent.OnDarkPsychologyClick(it))
                },
            )
        }
    }
}