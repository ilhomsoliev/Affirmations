package com.ilhomsoliev.affirmations.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilhomsoliev.affirmations.R
import com.ilhomsoliev.affirmations.core.Screens
import com.ilhomsoliev.affirmations.presentation.addAffirmation.AddAffirmationEvent
import com.ilhomsoliev.affirmations.presentation.addAffirmation.AddAffirmationScreen
import com.ilhomsoliev.affirmations.presentation.addAffirmation.AddAffirmationViewModel
import com.ilhomsoliev.affirmations.presentation.categories.CategoriesEvent
import com.ilhomsoliev.affirmations.presentation.categories.CategoriesScreen
import com.ilhomsoliev.affirmations.presentation.categories.CategoriesViewModel
import com.ilhomsoliev.affirmations.presentation.contentPreferences.ContentPreferencesEvent
import com.ilhomsoliev.affirmations.presentation.contentPreferences.ContentPreferencesScreen
import com.ilhomsoliev.affirmations.presentation.contentPreferences.ContentPreferencesViewModel
import com.ilhomsoliev.affirmations.presentation.favorties.FavoritesEvent
import com.ilhomsoliev.affirmations.presentation.favorties.FavoritesScreen
import com.ilhomsoliev.affirmations.presentation.favorties.FavoritesViewModel
import com.ilhomsoliev.affirmations.presentation.languages.LanguagesEvent
import com.ilhomsoliev.affirmations.presentation.languages.LanguagesScreen
import com.ilhomsoliev.affirmations.presentation.languages.LanguagesViewModel
import com.ilhomsoliev.affirmations.presentation.main.MainEvent
import com.ilhomsoliev.affirmations.presentation.main.MainScreen
import com.ilhomsoliev.affirmations.presentation.main.MainViewModel
import com.ilhomsoliev.affirmations.presentation.notifications.NotificationsEvent
import com.ilhomsoliev.affirmations.presentation.notifications.NotificationsScreen
import com.ilhomsoliev.affirmations.presentation.notifications.NotificationsViewModel
import com.ilhomsoliev.affirmations.presentation.settings.SettingsEvent
import com.ilhomsoliev.affirmations.presentation.settings.SettingsScreen
import com.ilhomsoliev.affirmations.presentation.settings.SettingsViewModel
import com.ilhomsoliev.affirmations.presentation.widgets.WidgetsScreen

@Composable
fun Navigation(
    onSetAlarm: (Int) -> Unit,
    onCancelAlarm: () -> Unit,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {
            Image(
                painter = painterResource(id = R.drawable.oregon),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(viewModel.state.collectAsState().value, onEvent = { event: MainEvent ->
                when (event) {
                    is MainEvent.NavigateToSettings -> {
                        navController.navigate(Screens.SettingsScreen.route)
                    }
                    is MainEvent.NavigateToCategories -> {
                        navController.navigate(Screens.CategoriesScreen.route)
                    }
                    else -> viewModel.onEvent(event)
                }
            })
        }
        composable(route = Screens.SettingsScreen.route) {
            val viewModel = hiltViewModel<SettingsViewModel>()
            SettingsScreen(state = viewModel.state.collectAsState().value, onEvent = { event ->
                when (event) {
                    is SettingsEvent.OnBackClick -> {
                        navController.popBackStack()
                    }
                    is SettingsEvent.GoToNotifications -> {
                        navController.navigate(Screens.NotificationsScreen.route)
                    }
                    is SettingsEvent.GoToAddAffirmation -> {
                        navController.navigate(Screens.AddAffirmationScreen.route)
                    }
                    is SettingsEvent.GoToLanguages -> {
                        navController.navigate(Screens.LanguageScreen.route)
                    }
                    is SettingsEvent.GoToWidgets -> {
                        navController.navigate(Screens.WidgetsScreen.route)
                    }
                    is SettingsEvent.GoToContentPreferences -> {
                        navController.navigate(Screens.ContentPreferencesScreen.route)
                    }
                    is SettingsEvent.GoToFavorites -> {
                        navController.navigate(Screens.FavoritesScreen.route)
                    }
                    else -> {
                        viewModel.onEvent(event)
                    }
                }
            })
        }
        composable(route = Screens.NotificationsScreen.route) {
            val viewModel = hiltViewModel<NotificationsViewModel>()
            val state = viewModel.state.collectAsState().value
            NotificationsScreen(state = state, onEvent = { event ->
                when (event) {
                    is NotificationsEvent.OnBackClick -> {
                        navController.popBackStack()
                    }
                    is NotificationsEvent.OnIsNotificationValueChange -> {
                        viewModel.onEvent(NotificationsEvent.OnIsNotificationValueChange(event.value))
                        if (event.value) {
                            onSetAlarm(state.startTime)
                        } else {
                            onCancelAlarm()
                        }
                    }
                    else -> {
                        viewModel.onEvent(event)
                    }
                }
            })
        }
        composable(route = Screens.AddAffirmationScreen.route) {
            val viewModel = hiltViewModel<AddAffirmationViewModel>()
            AddAffirmationScreen(
                state = viewModel.state.collectAsState().value,
                onEvent = { event ->
                    when (event) {
                        is AddAffirmationEvent.OnBackClick -> {
                            navController.popBackStack()
                        }

                        else -> {
                            viewModel.onEvent(event)
                        }
                    }
                })
        }
        composable(route = Screens.FavoritesScreen.route) {
            val viewModel = hiltViewModel<FavoritesViewModel>()
            FavoritesScreen(
                state = viewModel.state.collectAsState().value,
                onEvent = { event ->
                    when (event) {
                        is FavoritesEvent.OnBackClick -> {
                            navController.popBackStack()
                        }
                        else -> {
                            viewModel.onEvent(event)
                        }
                    }
                })
        }
        composable(route = Screens.ContentPreferencesScreen.route) {
            val viewModel = hiltViewModel<ContentPreferencesViewModel>()
            ContentPreferencesScreen(
                state = viewModel.state.collectAsState().value,
                onEvent = { event ->
                    when (event) {
                        is ContentPreferencesEvent.OnBackClick -> {
                            navController.popBackStack()
                        }
                        else -> {
                            viewModel.onEvent(event)
                        }
                    }
                })
        }
        composable(route = Screens.CategoriesScreen.route) {
            val viewModel = hiltViewModel<CategoriesViewModel>()
            CategoriesScreen(
                state = viewModel.state.collectAsState().value,
                onEvent = { event ->
                    when (event) {
                        is CategoriesEvent.OnBackClick -> {
                            navController.popBackStack()
                        }

                        else -> {
                            viewModel.onEvent(event)
                        }
                    }
                })
        }
        composable(route = Screens.WidgetsScreen.route) {
            WidgetsScreen(onBackClick = {
                navController.popBackStack()
            })
        }
        composable(route = Screens.LanguageScreen.route) {
            val viewModel = hiltViewModel<LanguagesViewModel>()
            LanguagesScreen(state = viewModel.state.collectAsState().value, onEvent = { event ->
                when (event) {
                    is LanguagesEvent.OnBackClick -> {
                        navController.popBackStack()
                    }
                    else -> {
                        viewModel.onEvent(event)
                    }
                }
            })
        }
    }
}