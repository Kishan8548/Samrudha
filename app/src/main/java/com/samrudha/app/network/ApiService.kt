package com.samrudha.app.network

import com.samrudha.app.model.LanguageLocationResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("https://wft-geo-db.p.rapidapi.com/v1/geo/cities?countryIds=IN")  // Replace with the actual API URL
    fun getLanguagesAndLocations(): Call<LanguageLocationResponse>
}
