package com.lavariyalabs.snapy.android.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * NavigationState - Manages navigation state without routes
 * 
 * Uses a simple stack-based navigation approach
 */
class NavigationState(
    initialScreen: Screen = Screen.Splash
) {
    private val navigationStack = mutableListOf<Screen>()
    
    var currentScreen: Screen by mutableStateOf(initialScreen)
        private set
    
    init {
        navigationStack.add(initialScreen)
    }
    
    /**
     * Navigate to a new screen
     */
    fun navigateTo(screen: Screen) {
        navigationStack.add(screen)
        currentScreen = screen
    }
    
    /**
     * Navigate back to previous screen
     */
    fun navigateBack(): Boolean {
        if (navigationStack.size > 1) {
            navigationStack.removeLast()
            currentScreen = navigationStack.last()
            return true
        }
        return false
    }
    
    /**
     * Navigate to a screen and clear the back stack
     */
    fun navigateToAndClearStack(screen: Screen) {
        navigationStack.clear()
        navigationStack.add(screen)
        currentScreen = screen
    }
    
    /**
     * Check if back navigation is possible
     */
    fun canGoBack(): Boolean = navigationStack.size > 1
}
