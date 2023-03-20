package com.ilhomsoliev.affirmations.presentation.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.ilhomsoliev.affirmations.core.Helper

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(state: MainState, onEvent: (MainEvent) -> Unit) {

    val pagerState = rememberPagerState()
    val context = LocalContext.current

    LaunchedEffect(key1 = state.selectedCategory, block = {
        if (state.affirmations.isNotEmpty()) {
            pagerState.scrollToPage(0)
        }
    })
    if (state.selectedCategory != null) {
        if (state.affirmations.isNotEmpty()) {
            VerticalPager(
                modifier = Modifier.fillMaxSize(),
                count = state.affirmations.size,
                state = pagerState,
            ) { currentPage ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        modifier = Modifier.padding(horizontal = 18.dp),
                        text = state.affirmations[currentPage].content,
                        fontSize = 21.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(modifier = Modifier.clip(CircleShape), onClick = {
                        onEvent(MainEvent.NavigateToCategories)
                    }) {
                        Row(
                            modifier = Modifier.padding(vertical = 2.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(imageVector = Icons.Default.Category, contentDescription = null)
                            Text(text = state.selectedCategory.name)
                        }
                    }
                    IconButton(onClick = { onEvent(MainEvent.NavigateToSettings) }) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(
                                    width = 1.dp, color = MaterialTheme.colors.primary,
                                    CircleShape
                                )
                                .background(Color.White)
                        ) {
                            Icon(
                                modifier = Modifier.padding(6.dp),
                                imageVector = Icons.Default.Settings,
                                contentDescription = null,
                                tint = MaterialTheme.colors.primary,
                            )
                        }
                    }
                }
                if (state.affirmations.isNotEmpty()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            Helper.shareText(
                                context,
                                state.affirmations[pagerState.currentPage].content
                            )
                        }) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = {
                            onEvent(MainEvent.OnLikeClick(state.affirmations[pagerState.currentPage].id!!))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = if (state.affirmations[pagerState.currentPage].isFavorite == 1) Color.Red else Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}