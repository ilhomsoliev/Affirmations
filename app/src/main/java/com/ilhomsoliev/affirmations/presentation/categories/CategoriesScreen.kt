package com.ilhomsoliev.affirmations.presentation.categories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ilhomsoliev.affirmations.core.Helper
import com.ilhomsoliev.affirmations.presentation.categories.components.CategoryItem
import com.ilhomsoliev.affirmations.presentation.components.CustomTopAppBar

@Composable
fun CategoriesScreen(
    state: CategoriesState,
    onEvent: (CategoriesEvent) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CustomTopAppBar(text = "Categories", onIconClick = {
            onEvent(CategoriesEvent.OnBackClick)
        })
        if (state.selectedCategory != null && state.categories.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(state.categories) {
                    CategoryItem(
                        name = it.name,
                        background = Helper.getBackgroundFromCategoryId(it.id!!),
                        onClick = {
                            onEvent(CategoriesEvent.OnItemClick(it.id))
                        },
                        isSelected = it.id == state.selectedCategory.id
                    )
                }
            }
        }
    }
}