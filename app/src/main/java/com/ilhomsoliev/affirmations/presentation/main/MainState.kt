package com.ilhomsoliev.affirmations.presentation.main

import com.ilhomsoliev.affirmations.data.local.model.Affirmation
import com.ilhomsoliev.affirmations.data.local.model.Category

data class MainState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val affirmations: List<Affirmation> = listOf(),
    val selectedCategory: Category? = null,
)