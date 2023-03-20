package com.ilhomsoliev.affirmations.presentation.addAffirmation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun AddAffirmationsMoreDialog(
    onSaveFavoritesClick: () -> Unit,
    onShareClick: () -> Unit,
    onEditClick: () -> Unit,
    onRemoveClick: () -> Unit,
    onDismiss: () -> Unit,
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
            ) {
                Box(modifier = Modifier.fillMaxWidth().clickable {
                    onSaveFavoritesClick()
                }){
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Save to favorites",
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier.fillMaxWidth().clickable {
                    onShareClick()
                }){
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Share",
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier.fillMaxWidth().clickable {
                    onEditClick()
                }){
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Edit",
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Box(modifier = Modifier.fillMaxWidth().clickable {
                    onRemoveClick()
                }){
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Remove",
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
    }
}