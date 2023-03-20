package com.ilhomsoliev.affirmations.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ilhomsoliev.affirmations.presentation.contentPreferences.ContentPreferencesEvent
import java.util.*

@Entity
data class Affirmation(
    @PrimaryKey
    var id: Int? = null,
    var createdAt: Long = System.currentTimeMillis(),
    var content: String,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Int = 0,
    @ColumnInfo(name = "isManual")
    var isManual: Int = 0,
    var isDarkPsychology: Int = 0,
    var categoryId: Int = -1,
)