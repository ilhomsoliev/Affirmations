package com.ilhomsoliev.affirmations.presentation.notifications.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TextInDe(
    text: String,
    value: String,
    isIncreaseButtonActive: Boolean = true,
    isDecreaseButtonActive: Boolean = true,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleWithIconInCenter(icon = Icons.Default.Remove, isActive = !isDecreaseButtonActive) {
                onDecreaseClick()
            }
            Text(modifier = Modifier.width(64.dp), text = value, textAlign = TextAlign.Center)
            CircleWithIconInCenter(icon = Icons.Default.Add, isActive = !isIncreaseButtonActive) {
                onIncreaseClick()
            }
        }
    }
}