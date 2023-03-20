package com.ilhomsoliev.affirmations.presentation.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.affirmations.core.DataStoreManager
import com.ilhomsoliev.affirmations.presentation.settings.SettingsEvent
import com.ilhomsoliev.affirmations.presentation.settings.SettingsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
) : ViewModel() {
    private val _state = MutableStateFlow(NotificationsState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        NotificationsState()
    )

    init {
        dataStoreManager.getNotificationRepetition.onEach { times ->
            _state.update {
                it.copy(repetitions = times)
            }
        }.launchIn(viewModelScope)

        dataStoreManager.getStartAtNotification.onEach { times ->
            _state.update {
                it.copy(startTime = times)
            }
        }.launchIn(viewModelScope)

        dataStoreManager.getEndAtNotification.onEach { times ->
            _state.update {
                it.copy(endTime = times)
            }
        }.launchIn(viewModelScope)
        dataStoreManager.getIsNotificationOn.onEach { value ->
            _state.update {
                it.copy(isNotificationOn = value)
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: NotificationsEvent) {
        when (event) {
            is NotificationsEvent.IncreaseRepetition -> {
                if (_state.value.repetitions == 10) return
                viewModelScope.launch {
                    dataStoreManager.updateNotificationsRepetition(_state.value.repetitions + 1)
                }
            }
            is NotificationsEvent.DecreaseRepetition -> {
                if (_state.value.repetitions == 1) return
                viewModelScope.launch {
                    dataStoreManager.updateNotificationsRepetition(_state.value.repetitions - 1)
                }
            }
            is NotificationsEvent.IncreaseStartTime -> {
                if (_state.value.endTime == _state.value.startTime + 1) return
                viewModelScope.launch {
                    dataStoreManager.updateStartAtNotification(_state.value.startTime + 1)
                }
            }
            is NotificationsEvent.DecreaseStartTime -> {
                if (_state.value.startTime - 1 == 0) return
                viewModelScope.launch {
                    dataStoreManager.updateStartAtNotification(_state.value.startTime - 1)
                }
            }
            is NotificationsEvent.IncreaseEndTime -> {
                if (_state.value.endTime + 1 > 24) return
                viewModelScope.launch {
                    dataStoreManager.updateEndAtNotification(_state.value.endTime + 1)
                }
            }
            is NotificationsEvent.DecreaseEndTime -> {
                if (_state.value.endTime - 1 <= _state.value.startTime) return
                viewModelScope.launch {
                    dataStoreManager.updateEndAtNotification(_state.value.endTime + 1)
                }
            }
            is NotificationsEvent.OnIsNotificationValueChange -> {
                viewModelScope.launch {
                    dataStoreManager.updateIsNotificationOn(event.value)
                }
            }
            else -> {

            }
        }
    }
}