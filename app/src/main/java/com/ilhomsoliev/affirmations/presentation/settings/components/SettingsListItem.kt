package com.ilhomsoliev.affirmations.presentation.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsListItem(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick()
        }) {
        Row(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = text, fontSize = 20.sp, fontWeight = FontWeight.Black)
        }
    }

}