package com.ilhomsoliev.affirmations.presentation.favorties

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.ilhomsoliev.affirmations.core.Helper
import com.ilhomsoliev.affirmations.presentation.components.CustomTopAppBar
import com.ilhomsoliev.affirmations.presentation.favorties.components.FavoriteAffirmationItem

@Composable
fun FavoritesScreen(
    state: FavoritesState,
    onEvent: (FavoritesEvent) -> Unit,
) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(text = "Favorites", onIconClick = {
            onEvent(FavoritesEvent.OnBackClick)
        })

        LazyColumn() {
            items(state.affirmations) {
                FavoriteAffirmationItem(
                    text = it.content,
                    date = Helper.formatDate(it.createdAt),
                    onShareClick = {
                        Helper.shareText(context, it.content)
                    },
                    onRemoveClick = {
                        onEvent(FavoritesEvent.OnRemoveClick(it.id!!))
                    },
                )
            }
        }
    }
    if (state.affirmations.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "You have not added\nanything yet.",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Black
            )
        }
    }

}