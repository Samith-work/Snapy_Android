package com.lavariyalabs.snapy.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Unit - Flashcard library
 */
@Serializable
data class StudyUnit(
    val id: Long,
    @SerialName("term_id")
    val termId: Long,
    val name: String,
    val description: String = "",
    @SerialName("order_index")
    val orderIndex: Int = 0
)
