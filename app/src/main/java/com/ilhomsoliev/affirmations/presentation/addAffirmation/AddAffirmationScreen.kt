package com.ilhomsoliev.affirmations.presentation.addAffirmation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.affirmations.core.Helper
import com.ilhomsoliev.affirmations.presentation.addAffirmation.components.AddAffirmationDialog
import com.ilhomsoliev.affirmations.presentation.addAffirmation.components.AddAffirmationItem
import com.ilhomsoliev.affirmations.presentation.components.CustomTopAppBar

@Composable
fun AddAffirmationScreen(
    state: AddAffirmationState,
    onEvent: (AddAffirmationEvent) -> Unit,
) {
    val context = LocalContext.current
    if (state.isDialogActive) {
        AddAffirmationDialog(value = state.newAffirmationContent, onValueChange = {
            onEvent(AddAffirmationEvent.OnNewAffirmationChange(it))
        }, onDismiss = {
            onEvent(AddAffirmationEvent.OnDismissDialog)
        }, onSaveClick = {
            onEvent(AddAffirmationEvent.OnSaveClick)
        })
    }
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(text = "Added", onIconClick = {
            onEvent(AddAffirmationEvent.OnBackClick)
        })

        LazyColumn() {
            items(state.listAffirmation) {
                AddAffirmationItem(
                    text = it.content,
                    date = Helper.formatDate(it.createdAt),
                    onSaveFavoritesClick = {
                        onEvent(AddAffirmationEvent.OnAddFavoriteClick(it.id!!))
                    },
                    onShareClick = {
                        Helper.shareText(context, it.content)
                    },
                    onEditClick = {
                        onEvent(AddAffirmationEvent.OnEditClick(it.id!!))
                    },
                    onRemoveClick = {
                        onEvent(AddAffirmationEvent.OnRemoveClick(it.id!!))
                    },
                )
            }
        }
    }
    if (state.listAffirmation.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "You have not added\nanything yet.",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Black
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clip(CircleShape),
            onClick = {
                onEvent(AddAffirmationEvent.OnAddAffirmationClick)
            }) {
            Text(modifier = Modifier.padding(vertical = 12.dp), text = "Add affirmation")
        }
    }

}