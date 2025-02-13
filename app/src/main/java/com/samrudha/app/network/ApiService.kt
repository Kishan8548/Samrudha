package com.samrudha.app.network

import com.samrudha.app.model.LoginRequest
import com.samrudha.app.model.LoginResponse
import com.samrudha.app.model.RegisterRequest
import com.samrudha.app.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>
}
