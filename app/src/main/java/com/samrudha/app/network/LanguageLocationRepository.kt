package com.samrudha.app.network

import android.util.Log
import com.samrudha.app.model.LanguageLocationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LanguageLocationRepository {
    fun fetchLanguagesAndLocations(onResult: (LanguageLocationResponse?) -> Unit) {
        RetrofitClient.instance.getLanguagesAndLocations().enqueue(object : Callback<LanguageLocationResponse> {
            override fun onResponse(
                call: Call<LanguageLocationResponse>,
                response: Response<LanguageLocationResponse>
            ) {
                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<LanguageLocationResponse>, t: Throwable) {
                Log.e("API Error", t.message ?: "Unknown error")
                onResult(null)
            }
        })
    }
}
