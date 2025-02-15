package com.samrudha.app.network

import com.samrudha.app.model.LoginRequest
import com.samrudha.app.model.LoginResponse
import com.samrudha.app.model.RegisterRequest
import com.samrudha.app.model.RegisterResponse
import com.samrudha.app.model.requests.CropRequest
import com.samrudha.app.model.requests.FertilizerRequest
import com.samrudha.app.model.requests.YieldRequest
import com.samrudha.app.model.responses.CropResponse
import com.samrudha.app.model.responses.DiseaseResponse
import com.samrudha.app.model.responses.FertilizerResponse
import com.samrudha.app.model.responses.YieldResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    // Crop Disease Identification (Image Upload)
    @Multipart
    @POST("predict-disease")
    fun uploadImage(
        @Part image: MultipartBody.Part
    ): Call<DiseaseResponse>

    // Crop Recommendation
    @POST("recommend-crop")
    fun recommendCrop(@Body request: CropRequest): Call<CropResponse>

    // Fertilizer Recommendation
    @POST("recommend-fertilizer")
    fun recommendFertilizer(@Body request: FertilizerRequest): Call<FertilizerResponse>

    // Yield Prediction
    @POST("predict-yield")
    fun predictYield(@Body request: YieldRequest): Call<YieldResponse>
}
