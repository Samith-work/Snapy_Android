package com.lavariyalabs.snapy.android.data.model

import kotlinx.serialization.Serializable
import java.time.LocalDate

/**
 * User - Represents app user
 *
 */
@Serializable
data class User(
    val id: String,                    // UUID
    val authId: String,                 // Supabase Auth ID
    val mobileNumber: String? = null,
    val name: String = "",
    val dateOfBirth: String? = null,   // ISO format date
    val language: String = "en",
    val gradeId: Long? = null,
    val preferredSubjectId: Long? = null,
    val createdAt: String = "",
    val updatedAt: String = "",
    val lastLogin: String? = null
)
