package com.ilhomsoliev.affirmations.presentation.languages

import com.ilhomsoliev.affirmations.presentation.favorties.FavoritesEvent

sealed class LanguagesEvent {
    object OnBackClick : LanguagesEvent()
    data class OnLanguageItemClick(val id: Int) : LanguagesEvent()
}