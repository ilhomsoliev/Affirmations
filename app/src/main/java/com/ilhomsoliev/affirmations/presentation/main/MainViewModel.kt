package com.ilhomsoliev.affirmations.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.affirmations.core.DataStoreManager
import com.ilhomsoliev.affirmations.data.local.AffirmationDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val dao: AffirmationDao,
) : ViewModel() {
    var job: Job? = null

    init {
        viewModelScope.launch {
            dataStoreManager.getCategoryId.onEach { it ->
                val category = dao.getCategoryById(it)
                _state.update {
                    it.copy(selectedCategory = category)
                }
                job?.cancel()
                job = dao.getAffirmationsByCategoryId(it).onEach { list ->
                    if (_state.value.affirmations.isEmpty() || _state.value.selectedCategory?.id != it) {
                        _state.update {
                            it.copy(affirmations = list.shuffled())
                        }
                    } else {
                        val newList = _state.value.affirmations
                        if (list.none { el -> el.id == newList[0].id }) {
                            _state.update {
                                it.copy(affirmations = list.shuffled())
                            }
                        } else {
                            for (i in newList.indices) {
                                val isLiked =
                                    list.filter { el -> el.id == newList[i].id }[0].isFavorite
                                newList[i].isFavorite = isLiked
                            }
                            _state.update {
                                it.copy(
                                    affirmations = newList
                                )
                            }
                        }
                    }
                }.launchIn(viewModelScope)

            }.launchIn(viewModelScope)
        }
    }

    private val _state = MutableStateFlow(MainState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        MainState()
    )

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnLoad -> {

            }
            is MainEvent.OnLikeClick -> {
                viewModelScope.launch {
                    val affirmation = dao.getAffirmationById(event.id)!!
                    dao.insertAffirmation(affirmation.copy(isFavorite = if (affirmation.isFavorite == 1) 0 else 1))
                }
            }
            else -> {}
        }
    }


}