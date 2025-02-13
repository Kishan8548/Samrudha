package com.samrudha.app.model

data class LoginResponse(
    val success: Boolean,  // Ensure this matches the actual API response
    val token: String?
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val email: String,
    val password: String
)

data class RegisterResponse(
    val success: Boolean,
    val message: String
)
