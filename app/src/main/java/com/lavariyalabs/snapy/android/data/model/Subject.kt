package com.lavariyalabs.snapy.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Subject - Represents a subject
 */
@Serializable
data class Subject(
    val id: Long,
    @SerialName("grade_id") val gradeId: Long,
    val name: String,
    val code: String,
    val description: String = ""
)
