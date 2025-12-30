package com.lavariyalabs.snapy.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Term - First Term, Second Term, etc.
 */
@Serializable
data class Term(
    val id: Long,
    @SerialName("subject_id")
    val subjectId: Long,
    @SerialName("term_number")
    val termNumber: Int,
    val name: String,
    val description: String = ""
)
