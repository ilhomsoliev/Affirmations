package com.ilhomsoliev.affirmations.presentation.favorties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.affirmations.data.local.AffirmationDao
import com.ilhomsoliev.affirmations.presentation.addAffirmation.AddAffirmationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val dao: AffirmationDao,
) : ViewModel() {
    private val _state = MutableStateFlow(FavoritesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        FavoritesState()
    )

    init {
        dao.getFavoriteAffirmations().onEach { list ->
            _state.update {
                it.copy(
                    affirmations = list
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.OnRemoveClick -> {
                viewModelScope.launch {
                    val affirmation = dao.getAffirmationById(event.id)!!
                    dao.insertAffirmation(affirmation.copy(isFavorite = 0))
                    _state.value.isDialogActive = false
                }
            }
            else -> {

            }
        }
    }
}