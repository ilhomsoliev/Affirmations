package com.ilhomsoliev.affirmations.presentation.addAffirmation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddAffirmationItem(
    text: String,
    date: String,
    onSaveFavoritesClick: () -> Unit,
    onShareClick: () -> Unit,
    onEditClick: () -> Unit,
    onRemoveClick: () -> Unit,
) {
    val isDialogActive = remember { mutableStateOf(false) }
    if (isDialogActive.value) {
        AddAffirmationsMoreDialog(
            onSaveFavoritesClick = onSaveFavoritesClick,
            onShareClick = onShareClick,
            onEditClick = onEditClick,
            onRemoveClick = onRemoveClick,
            onDismiss = {
                isDialogActive.value = false
            })
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = text, fontWeight = FontWeight.Black)
                Text(text = date, fontSize = 14.sp, color = Color.Gray)
            }
            IconButton(onClick = {
                isDialogActive.value = true;
            }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Divider()
    }
}