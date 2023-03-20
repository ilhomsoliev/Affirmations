package com.ilhomsoliev.affirmations.presentation.addAffirmation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import okio.utf8Size

@Composable
fun AddAffirmationDialog(
    value: String,
    onValueChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onSaveClick: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Add a new affirmation",
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = value,
                    onValueChange = onValueChange,
                    placeholder = {
                        Text(text = "Enter affirmation")
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 12.dp),
                    text = "${value.length}/1000",
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .clip(CircleShape), onClick = {
                        onSaveClick()
                }) {
                    Text(modifier = Modifier.padding(vertical = 12.dp), text = "Save")
                }
            }
        }
    }
}