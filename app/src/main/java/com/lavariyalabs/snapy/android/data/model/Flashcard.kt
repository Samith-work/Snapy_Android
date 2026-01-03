package com.lavariyalabs.snapy.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Flashcard - Supports SELF_EVAL and MCQ types
 */
@Serializable
data class Flashcard(
    val id: Long,
    @SerialName("unit_id")
    val unitId: Long,
    val type: String,                  // SELF_EVAL or MCQ
    val question: String,
    @SerialName("correct_answer")
    val correctAnswer: String? = null,  // For SELF_EVAL
    val explanation: String = "",
    val difficulty: String = "MEDIUM",  // EASY, MEDIUM, HARD
    @SerialName("order_index")
    val orderIndex: Int = 0,
    val quizOptions: List<QuizOption> = emptyList()  // For MCQ
)

/**
 * QuizOption - Multiple choice answer
 */
@Serializable
data class QuizOption(
    val id: Long,
    @SerialName("flashcard_id")
    val flashcardId: Long,
    @SerialName("option_text")
    val optionText: String,
    @SerialName("option_letter")
    val optionLetter: String,  // A, B, C, D
    @SerialName("is_correct")
    val isCorrect: Boolean,
    @SerialName("order_index")
    val orderIndex: Int
)

/**
 * UserProgress - SM2 spaced repetition tracking
 */
@Serializable
data class UserProgress(
    val id: Long,
    @SerialName("user_id")
    val userId: String,
    @SerialName("flashcard_id")
    val flashcardId: Long,
    @SerialName("total_reviews")
    val totalReviews: Int = 0,
    @SerialName("correct_reviews")
    val correctReviews: Int = 0,
    @SerialName("incorrect_reviews")
    val incorrectReviews: Int = 0,
    @SerialName("ease_factor")
    val easeFactor: Float = 2.5f,
    val interval: Int = 1,
    val repetitions: Int = 0,
    @SerialName("next_review_date")
    val nextReviewDate: String? = null,
    @SerialName("last_reviewed_at")
    val lastReviewedAt: String? = null,
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("updated_at")
    val updatedAt: String = ""
)

/**
 * QuizResponse - User's answer to a flashcard
 */
@Serializable
data class QuizResponse(
    val id: Long,
    @SerialName("user_id")
    val userId: String,
    @SerialName("flashcard_id")
    val flashcardId: Long,
    @SerialName("selected_option_id")
    val selectedOptionId: Long? = null,
    @SerialName("response_type")
    val responseType: String,  // CORRECT, INCORRECT, SKIPPED
    @SerialName("time_taken_seconds")
    val timeTakenSeconds: Int? = null,
    @SerialName("responded_at")
    val respondedAt: String = ""
)

/**
 * QuizSession - Session state during studying
 */
data class QuizSession(
    val totalCards: Int = 0,// Total cards in this session
    val currentCardIndex: Int = 0,// Which card user is viewing (0-indexed)
    val correctAnswers: Int = 0,
    val incorrectAnswers: Int = 0
) {
    val isQuizComplete: Boolean
        get() = currentCardIndex >= totalCards

    val progressPercent: Float
        get() = if (totalCards > 0) {
            ((currentCardIndex + 1) / totalCards.toFloat()) * 100
        } else {
            0f
        }
}
