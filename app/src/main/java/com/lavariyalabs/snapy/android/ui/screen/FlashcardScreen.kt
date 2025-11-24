package com.lavariyalabs.snapy.android.ui.screen

// File: com/lavariyalabs/snapy/android/ui/screen/FlashcardScreen.kt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lavariyalabs.snapy.android.ui.components.AnswerButtonsSection
import com.lavariyalabs.snapy.android.ui.components.FlashcardComponent
import com.lavariyalabs.snapy.android.ui.components.ProgressSection
import com.lavariyalabs.snapy.android.ui.viewmodel.FlashcardViewModel

/**
 * FlashcardScreen - Main screen composable
 *
 * STRUCTURE:
 * 1. Progress bar (top)
 * 2. Flashcard (center, main focus)
 * 3. Answer buttons (bottom)
 */
@Composable
fun FlashcardScreen(
    viewModel: FlashcardViewModel = viewModel()
) {
    // ========== INITIALIZE QUIZ ON FIRST LOAD ==========
    /**
     * LaunchedEffect():
     *
     * - Initialize data without rerunning every recomposition
     * - Prevents infinite loops
     */
    LaunchedEffect(Unit) {
        viewModel.initializeQuiz()
    }

    // ========== OBSERVE STATE FROM VIEWMODEL ==========
    /**
     * .value = Read the current value from State<T>
     * Composable automatically recomposes when value changes
     */
    val quizSession by viewModel.quizSession
    val isCardFlipped by viewModel.currentCardFlipped
    val currentCard = viewModel.getCurrentCard()

    // ========== MAIN UI LAYOUT ==========
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6))  // Light gray background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ========== TOP: PROGRESS SECTION ==========
            ProgressSection(
                currentCard = quizSession.currentCardIndex + 1,  // 0-indexed → 1-indexed
                totalCards = quizSession.totalCards,
                progressPercent = quizSession.progressPercent
            )

            // ========== CENTER: FLASHCARD (IF AVAILABLE) ==========
            if (currentCard != null) {
                Spacer(modifier = Modifier.weight(1f))  // Push card to center

                FlashcardComponent(
                    isFlipped = isCardFlipped,
                    question = currentCard.question,
                    answer = currentCard.answer,
                    onCardClick = { viewModel.toggleCardFlip() }
                )

                Spacer(modifier = Modifier.weight(1f))  // Push buttons to bottom
            }

            // ========== BOTTOM: ANSWER BUTTONS ==========
            AnswerButtonsSection(
                onDidntKnow = { viewModel.recordAnswer(isCorrect = false) },
                onKnew = { viewModel.recordAnswer(isCorrect = true) }
            )
        }
    }
}
