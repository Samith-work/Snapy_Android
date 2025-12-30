package com.lavariyalabs.snapy.android.data.model

import kotlinx.serialization.Serializable

/**
 * Grade - Represents a grade
 */
@Serializable
data class Grade(
    val id: Long,
    val name: String,
    val description: String = ""
)
