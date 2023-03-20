package com.ilhomsoliev.affirmations.presentation.languages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.affirmations.presentation.components.CustomTopAppBar
import com.ilhomsoliev.affirmations.presentation.favorties.FavoritesEvent
import com.ilhomsoliev.affirmations.presentation.languages.components.LanguageItem

@Composable
fun LanguagesScreen(
    state: LanguagesState,
    onEvent: (LanguagesEvent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(text = "Favorites", onIconClick = {
            onEvent(LanguagesEvent.OnBackClick)
        })
        if (state.selectedLanguage != null) {
            LazyColumn() {
                items(state.languages) {
                    LanguageItem(name = it.name, isActive = it.id == state.selectedLanguage.id) {
                        onEvent(LanguagesEvent.OnLanguageItemClick(it.id))
                    }
                }
            }
        }
    }
}