package com.ilhomsoliev.affirmations.presentation.notifications.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CircleWithTextInCenter(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
) {
    val activeOnModifier = Modifier
        .size(42.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colors.primary)
    val activeOffModifier = Modifier
        .size(42.dp)
        .clip(CircleShape)
        .border(1.dp, MaterialTheme.colors.primary, CircleShape)
    Box(
        modifier = (if (isActive) activeOnModifier else activeOffModifier).clickable {
            onClick()
        },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isActive) Color.White else MaterialTheme.colors.primary,
            textAlign = TextAlign.Center
        )
    }

}