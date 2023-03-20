package com.ilhomsoliev.affirmations.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.affirmations.core.DataStoreManager
import com.ilhomsoliev.affirmations.data.local.AffirmationDao
import com.ilhomsoliev.affirmations.presentation.contentPreferences.ContentPreferencesEvent
import com.ilhomsoliev.affirmations.presentation.contentPreferences.ContentPreferencesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val dao: AffirmationDao,
) : ViewModel() {
    private val _state = MutableStateFlow(CategoriesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        CategoriesState()
    )

    init {
        dao.getAllCategories().onEach { list ->
            _state.update {
                it.copy(categories = list)
            }
        }.launchIn(viewModelScope)
        dataStoreManager.getCategoryId.onEach {
            val category = dao.getCategoryById(it)
            _state.update {
                it.copy(selectedCategory = category)
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: CategoriesEvent) {
        when (event) {
            is CategoriesEvent.OnItemClick -> {
                viewModelScope.launch {
                    dataStoreManager.updateCategoryId(event.id)
                }
            }
            else -> {

            }
        }
    }
}