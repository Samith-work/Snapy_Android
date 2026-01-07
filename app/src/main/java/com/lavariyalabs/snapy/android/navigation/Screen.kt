package com.lavariyalabs.snapy.android.navigation

/**
 * Screen - Sealed class representing all app screens
 * 
 * This replaces string-based routes with type-safe navigation
 */
sealed class Screen {
    object Splash : Screen()
    object OnboardingLanguage : Screen()
    object OnboardingName : Screen()
    object OnboardingGrade : Screen()
    object OnboardingSubject : Screen()
    object Home : Screen()
    object Profile : Screen()
    data class Flashcard(val unitId: Long) : Screen()
}
