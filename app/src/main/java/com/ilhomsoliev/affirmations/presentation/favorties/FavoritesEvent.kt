package com.ilhomsoliev.affirmations.presentation.favorties

import com.ilhomsoliev.affirmations.presentation.addAffirmation.AddAffirmationEvent

sealed class FavoritesEvent {
    object OnBackClick : FavoritesEvent()
    object OnDismissDialog : FavoritesEvent()
    data class OnRemoveClick(val id: Int) : FavoritesEvent()
}