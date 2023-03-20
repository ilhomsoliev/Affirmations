package com.ilhomsoliev.affirmations.presentation.notifications.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WeekDays(

) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircleWithTextInCenter(text = "Sun", isActive = false, onClick = {

        })
        CircleWithTextInCenter(text = "Mon", isActive = true, onClick = {

        })
        CircleWithTextInCenter(text = "Tue", isActive = false, onClick = {

        })
        CircleWithTextInCenter(text = "Wed", isActive = false, onClick = {

        })
        CircleWithTextInCenter(text = "Thu", isActive = false, onClick = {

        })
        CircleWithTextInCenter(text = "Fri", isActive = false, onClick = {

        })

        CircleWithTextInCenter(text = "Sat", isActive = false, onClick = {

        })

    }

}