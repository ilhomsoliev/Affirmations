package com.ilhomsoliev.affirmations.presentation.favorties

import com.ilhomsoliev.affirmations.data.local.model.Affirmation

data class FavoritesState(
    val affirmations:List<Affirmation> = listOf(),
    var isDialogActive: Boolean = false,
)