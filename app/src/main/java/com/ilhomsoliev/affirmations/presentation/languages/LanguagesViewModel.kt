package com.ilhomsoliev.affirmations.presentation.languages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.affirmations.core.DataStoreManager
import com.ilhomsoliev.affirmations.core.Helper
import com.ilhomsoliev.affirmations.presentation.favorties.FavoritesEvent
import com.ilhomsoliev.affirmations.presentation.favorties.FavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguagesViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {
    private val _state = MutableStateFlow(LanguagesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        LanguagesState()
    )

    init {
        dataStoreManager.getLanguageId.onEach { id ->
            _state.update {
                it.copy(selectedLanguage = Helper.getLanguages().filter { it.id == id }[0])
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: LanguagesEvent) {
        when (event) {
            is LanguagesEvent.OnLanguageItemClick -> {
                viewModelScope.launch {
                    dataStoreManager.updateLanguage(event.id)
                }
            }
            else -> {

            }
        }
    }
}