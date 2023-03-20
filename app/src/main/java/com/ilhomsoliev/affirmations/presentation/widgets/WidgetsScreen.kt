package com.ilhomsoliev.affirmations.presentation.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.affirmations.presentation.components.CustomTopAppBar

@Composable
fun WidgetsScreen(
    onBackClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(text = "Widgets", onIconClick = {
            onBackClick()
        })
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                modifier = Modifier.size(42.dp),
                imageVector = Icons.Default.Widgets,
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Step 1",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "On your phone's Home screen, touch and hold an empty space.",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Step 2",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(text = "Tap Widgets.", textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Step 3",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Slide the widget to where you want it. Lift your finger.",
                textAlign = TextAlign.Center
            )
        }
    }
}