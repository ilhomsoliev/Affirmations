package com.ilhomsoliev.affirmations.presentation.addAffirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.affirmations.data.local.AffirmationDao
import com.ilhomsoliev.affirmations.data.local.model.Affirmation
import com.ilhomsoliev.affirmations.presentation.notifications.NotificationsEvent
import com.ilhomsoliev.affirmations.presentation.notifications.NotificationsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import javax.inject.Inject

@HiltViewModel
class AddAffirmationViewModel @Inject constructor(
    private val dao: AffirmationDao,
) : ViewModel() {
    private val _state = MutableStateFlow(AddAffirmationState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AddAffirmationState()
    )

    init {
        dao.getManualAffirmation().onEach { list ->
            _state.update {
                it.copy(
                    listAffirmation = list
                )
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: AddAffirmationEvent) {
        when (event) {
            is AddAffirmationEvent.OnNewAffirmationChange -> {
                if (event.value.length > 1000) return
                _state.update {
                    it.copy(newAffirmationContent = event.value)
                }
            }
            is AddAffirmationEvent.OnSaveClick -> {
                viewModelScope.launch {
                    dao.insertAffirmation(
                        Affirmation(
                            id = if (_state.value.editAffirmationId == -1) null else _state.value.editAffirmationId,
                            content = _state.value.newAffirmationContent,
                            isManual = 1,
                            isFavorite = 0,
                            categoryId = 1
                        )
                    )
                }
                _state.update {
                    it.copy(isDialogActive = false, newAffirmationContent = "")
                }
            }
            is AddAffirmationEvent.OnAddAffirmationClick -> {
                _state.update {
                    it.copy(isDialogActive = true)
                }
            }
            is AddAffirmationEvent.OnDismissDialog -> {
                _state.update {
                    it.copy(isDialogActive = false)
                }
            }
            is AddAffirmationEvent.OnRemoveClick -> {
                viewModelScope.launch {
                    dao.deleteAffirmation(event.id)
                }
            }
            is AddAffirmationEvent.OnAddFavoriteClick -> {
                viewModelScope.launch {
                    val affirmation = dao.getAffirmationById(event.id)
                    dao.insertAffirmation(affirmation!!.copy(id = event.id, isFavorite = 1))
                    _state.update {
                        it.copy(isDialogActive = false)
                    }
                }
            }
            is AddAffirmationEvent.OnEditClick -> {
                viewModelScope.launch {
                    _state.update {
                        it.copy(editAffirmationId = event.id)
                    }
                    val content = dao.getAffirmationById(event.id)!!.content
                    _state.update {
                        it.copy(newAffirmationContent = content, isDialogActive = true)
                    }

                }
            }
            else -> {}
        }
    }
}