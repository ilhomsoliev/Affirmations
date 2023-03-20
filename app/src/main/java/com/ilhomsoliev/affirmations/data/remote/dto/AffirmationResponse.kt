package com.ilhomsoliev.affirmations.data.remote.dto

import com.ilhomsoliev.affirmations.data.local.model.Affirmation

data class AffirmationResponse(
    val id: Int,
    val affirmation: String,
    val isFavorite: Int,
    val isManual: Int,
)