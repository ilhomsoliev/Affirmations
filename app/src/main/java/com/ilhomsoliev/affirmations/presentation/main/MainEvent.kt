package com.ilhomsoliev.affirmations.presentation.main

sealed class MainEvent {
    object OnLoad : MainEvent()
    data class OnLikeClick(val id: Int) : MainEvent()
    object NavigateToSettings : MainEvent()
    object NavigateToCategories : MainEvent()

}