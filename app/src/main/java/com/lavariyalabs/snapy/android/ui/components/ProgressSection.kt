package com.lavariyalabs.snapy.android.ui.components

// File: com/lavariyalabs/snapy/android/ui/components/ProgressSection.kt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * ProgressSection Composable
 *
 * WHAT IS A COMPOSABLE?
 * - A function marked with @Composable that describes UI
 * - Can accept parameters (like data)
 * - Returns UI elements (Compose components)
 * - Gets recomposed when state changes
 */
@Composable
fun ProgressSection(
    currentCard: Int,           // Example: card 3 of 10
    totalCards: Int,            // Total cards in session
    progressPercent: Float      // 0f to 100f
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF6366F1)  // Indigo color
            )
            .padding(16.dp)
    ) {
        // ========== CARD COUNTER TEXT ==========
        Text(
            text = "$currentCard / $totalCards",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // ========== PROGRESS BAR ==========
        LinearProgressIndicator(
            progress = { progressPercent / 100f },  // Convert % to 0-1 range
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = Color(0xFF10B981),              // Green color for progress
            trackColor = Color.White.copy(alpha = 0.3f)  // Transparent white background
        )
    }
}
