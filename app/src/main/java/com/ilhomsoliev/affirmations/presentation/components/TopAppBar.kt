package com.ilhomsoliev.affirmations.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.affirmations.presentation.notifications.NotificationsEvent

@Composable
fun CustomTopAppBar(
    icon: ImageVector? = Icons.Default.ArrowBack,
    text: String,
    onIconClick: () -> Unit = {}
) {

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        icon?.let {
            IconButton(onClick = {
                onIconClick()
            }) {
                Icon(imageVector = icon, contentDescription = null)
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 22.sp, fontWeight = FontWeight.Bold)
    }
}