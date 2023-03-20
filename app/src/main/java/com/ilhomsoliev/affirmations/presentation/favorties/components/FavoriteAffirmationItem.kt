package com.ilhomsoliev.affirmations.presentation.favorties.components

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
import com.ilhomsoliev.affirmations.presentation.addAffirmation.components.AddAffirmationsMoreDialog

@Composable
fun FavoriteAffirmationItem(
    text: String,
    date: String,
    onShareClick: () -> Unit,
    onRemoveClick: () -> Unit
) {
    val isDialogActive = remember { mutableStateOf(false) }
    if (isDialogActive.value) {
        FavoritesDialog(
            onShareClick = {
                onShareClick()
                isDialogActive.value = false
            },
            onRemoveClick = {
                isDialogActive.value = false
                onRemoveClick()
            },
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
            Column(modifier = Modifier.weight(10f)) {
                Text(text = text, fontWeight = FontWeight.Black)
                Text(text = date, fontSize = 14.sp, color = Color.Gray)
            }
            IconButton(modifier = Modifier.weight(1f), onClick = {
                isDialogActive.value = true;
            }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Divider()
    }
}