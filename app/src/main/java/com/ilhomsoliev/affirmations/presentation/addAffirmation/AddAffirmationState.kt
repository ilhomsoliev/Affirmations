package com.ilhomsoliev.affirmations.presentation.addAffirmation

import com.ilhomsoliev.affirmations.data.local.model.Affirmation

data class AddAffirmationState(
    val listAffirmation: List<Affirmation> = listOf(),
    val editAffirmationId: Int = -1,
    val newAffirmationContent: String = "",
    val isDialogActive: Boolean = false,
)