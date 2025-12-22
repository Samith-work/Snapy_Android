package com.lavariyalabs.snapy.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lavariyalabs.snapy.android.ui.theme.Snapy_AndroidTheme
import com.lavariyalabs.snapy.android.ui.screen.FlashcardScreen
import com.lavariyalabs.snapy.android.ui.screen.HomeScreen
import com.lavariyalabs.snapy.android.ui.screen.OnboardingScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Snapy_AndroidTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Box(modifier = Modifier
//                        .fillMaxSize()
//                        .padding(innerPadding)
//                    ) {
//                        HomeScreen()
//                    }
//                }
//                val navController = rememberNavController()
//
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    // 2. Define the NavHost inside the Scaffold
//                    NavHost(
//                        navController = navController,
//                        startDestination = "onboarding", // Start app here
//                        modifier = Modifier.padding(innerPadding)
//                    ) {
//                        // 3. Define the Onboarding Route
//                        composable("onboarding") {
//                            OnboardingScreen(onNextClick = {
//                                // Navigate to Home and remove Onboarding from history
//                                navController.navigate("home") {
//                                    popUpTo("onboarding") { inclusive = true }
//                                }
//                            })
//                        }
//
//                        // 4. Define the Home Route
//                        composable("home") {
//                            HomeScreen()
//                        }
//                    }
//                }
            }
        }
    }
}

@Composable
fun LottieLoadingAnimation(modifier: Modifier = Modifier) {
    // 1. Load the Lottie composition from the raw resource
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.loading_animation)
    )

    // 2. Control the animation playback (e.g., infinite loop, auto-play)
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever, // Loop infinitely
        isPlaying = true // Start playing automatically
    )

    // 3. Display the animation
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier.size(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun FlashcardScreenPreview() {
    Snapy_AndroidTheme {
        FlashcardScreen()
    }
}
