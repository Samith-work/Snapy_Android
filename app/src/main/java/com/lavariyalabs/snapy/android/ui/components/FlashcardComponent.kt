package com.lavariyalabs.snapy.android.ui.components

// File: com/lavariyalabs/snapy/android/ui/components/FlashcardComponent.kt

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * FlashcardComponent - The main interactive flashcard
 *
 * KEY FEATURES:
 * - Flips between question (front) and answer (back)
 * - Colorful gradient backgrounds
 * - Smooth animation
 * - Clickable to trigger flip
 */
@Composable
fun FlashcardComponent(
    isFlipped: Boolean,         // true = showing answer, false = showing question
    question: String,           // Question text (front side)
    answer: String,             // Answer text (back side)
    onCardClick: () -> Unit     // Callback when user clicks to flip
) {
    // ========== ANIMATION SETUP ==========
    /**
     * animateFloatAsState():
     * - Smoothly animates from current value to target value
     * - WHY use this?
     *   - Provides smooth transition (not instant jump)
     *   - Handled by Compose, no manual animation code needed
     *   - tween() = linear animation over 500ms
     *
     * rotationY will be:
     * - 0f when isFlipped = false (front side, normal)
     * - 180f when isFlipped = true (back side, rotated)
     */
    val rotation: Float by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "cardFlip"
    )

    // ========== FLASHCARD UI ==========
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .aspectRatio(1f)  // Square card (height = width)
            .clip(RoundedCornerShape(20.dp))
            .graphicsLayer {
                // 3D rotation effect - rotate around Y axis
                rotationY = rotation
                cameraDistance = 12f * density  // Depth perception
            }
            .background(
                color = if (isFlipped)
                    Color(0xFFEC4899)  // Pink for answer side
                else
                    Color(0xFF3B82F6)  // Blue for question side
            )
            .clickable(enabled = true) { onCardClick() },
        contentAlignment = Alignment.Center
    ) {
        // ========== CARD CONTENT ==========
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Side indicator text
            Text(
                text = if (isFlipped) "ANSWER" else "QUESTION",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Main content (question or answer)
            Text(
                text = if (isFlipped) answer else question,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()  // Smooth size change animation
            )

            // Hint text
            Text(
                text = "Tap to ${if (isFlipped) "see question" else "see answer"}",
                color = Color.White.copy(alpha = 0.5f),
                fontSize = 11.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
