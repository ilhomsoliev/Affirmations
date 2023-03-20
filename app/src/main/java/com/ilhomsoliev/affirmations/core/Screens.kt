package com.ilhomsoliev.affirmations.core

sealed class Screens(val route:String) {
    object MainScreen :Screens("MainScreen")
    object SettingsScreen :Screens("SettingsScreen")
    object NotificationsScreen :Screens("NotificationsScreen")
    object ContentPreferencesScreen :Screens("ContentPreferencesScreen")
    object LanguageScreen :Screens("LanguageScreen")
    object WidgetsScreen :Screens("WidgetsScreen")
    object FavoritesScreen :Screens("FavoritesScreen")
    object AddAffirmationScreen :Screens("AddAffirmationScreen")
    object CategoriesScreen :Screens("CategoriesScreen")
}