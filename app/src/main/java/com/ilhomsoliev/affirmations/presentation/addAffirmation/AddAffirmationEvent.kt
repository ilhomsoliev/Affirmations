package com.ilhomsoliev.affirmations.presentation.addAffirmation

sealed class AddAffirmationEvent {
    object OnAddAffirmationClick : AddAffirmationEvent()
    object OnSaveClick : AddAffirmationEvent()
    object OnBackClick : AddAffirmationEvent()
    object OnDismissDialog : AddAffirmationEvent()
    data class OnNewAffirmationChange(val value: String) : AddAffirmationEvent()
    data class OnAddFavoriteClick(val id: Int) : AddAffirmationEvent()
    data class OnRemoveClick(val id: Int) : AddAffirmationEvent()
    data class OnEditClick(val id: Int) : AddAffirmationEvent()
}