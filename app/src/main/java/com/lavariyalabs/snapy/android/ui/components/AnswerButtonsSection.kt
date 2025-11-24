package com.lavariyalabs.snapy.android.ui.components

// File: com/lavariyalabs/snapy/android/ui/components/AnswerButtonsSection.kt

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * AnswerButtonsSection
 *
 * Two action buttons for user to indicate if they knew the answer or not
 * - Left button: "I didn't know" (negative response)
 * - Right button: "I knew" (positive response)
 */
@Composable
fun AnswerButtonsSection(
    onDidntKnow: () -> Unit,   // Callback for "I didn't know" button
    onKnew: () -> Unit          // Callback for "I knew" button
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // ========== "I DIDN'T KNOW" BUTTON ==========
        AnswerButton(
            text = "I didn't know",
            backgroundColor = Color(0xFFEF4444),  // Red for negative
            modifier = Modifier.weight(1f),
            onClick = onDidntKnow
        )

        // ========== "I KNEW" BUTTON ==========
        AnswerButton(
            text = "I knew",
            backgroundColor = Color(0xFF10B981),  // Green for positive
            modifier = Modifier.weight(1f),
            onClick = onKnew
        )
    }
}

/**
 * AnswerButton - Reusable button component
 */
@Composable
private fun AnswerButton(
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
