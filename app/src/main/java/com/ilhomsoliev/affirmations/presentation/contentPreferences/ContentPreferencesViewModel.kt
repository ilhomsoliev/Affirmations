package com.ilhomsoliev.affirmations.presentation.contentPreferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.affirmations.core.DataStoreManager
import com.ilhomsoliev.affirmations.presentation.favorties.FavoritesEvent
import com.ilhomsoliev.affirmations.presentation.favorties.FavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentPreferencesViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {
    private val _state = MutableStateFlow(ContentPreferencesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ContentPreferencesState()
    )

    init {
        dataStoreManager.getIsDarkPsychology.onEach { value ->
            _state.update {
                it.copy(isDarkPsychology = value)
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: ContentPreferencesEvent) {
        when (event) {
            is ContentPreferencesEvent.OnDarkPsychologyClick -> {
                viewModelScope.launch {
                    dataStoreManager.updateIsDarkPsychologyActive(event.value)
                }
            }
            else -> {

            }
        }
    }

}