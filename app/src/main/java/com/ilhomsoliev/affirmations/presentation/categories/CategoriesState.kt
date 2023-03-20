package com.ilhomsoliev.affirmations.presentation.categories

import com.ilhomsoliev.affirmations.data.local.model.Category

data class CategoriesState(
    val categories: List<Category> = listOf(),
    val selectedCategory: Category? = null,
)