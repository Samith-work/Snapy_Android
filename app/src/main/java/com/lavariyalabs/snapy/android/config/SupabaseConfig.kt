package com.lavariyalabs.snapy.android.config

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

/**
 * SupabaseConfig - Singleton object that initializes Supabase
 */
object SupabaseConfig {

    // ========== YOUR CREDENTIALS ==========
    private const val SUPABASE_URL = "https://supabasekong-xoogo0gsgcws488w4ksscc8c.213.199.37.37.sslip.io"
    private const val SUPABASE_ANON_KEY = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzdXBhYmFzZSIsImlhdCI6MTc2ODA5NTkwMCwiZXhwIjo0OTIzNzY5NTAwLCJyb2xlIjoiYW5vbiJ9.Z6NixD73ybpP5C9C4Wr2myit9WzLEczxiaRT01W4s8"

    /**
     * supabaseClient - The main Supabase client
     *
     * Used to make requests to your database
     * Initialized once when app starts
     */
    val supabaseClient = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_ANON_KEY
    ) {
        // Configure which modules to use
        install(Postgrest)  // For database queries
        install(Auth)       // For user authentication (future)

        // Configure timeouts using the public API
        // This replaces the internal httpConfig usage
        requestTimeout = 60.seconds

        // Configure JSON serialization to be more lenient
        defaultSerializer = KotlinXSerializer(Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            encodeDefaults = true
        })
    }
}
