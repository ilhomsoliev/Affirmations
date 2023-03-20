package com.ilhomsoliev.affirmations.presentation.categories

import com.ilhomsoliev.affirmations.presentation.contentPreferences.ContentPreferencesEvent

sealed class CategoriesEvent {
    data class OnItemClick(val id: Int) : CategoriesEvent()

    object OnBackClick : CategoriesEvent()

}