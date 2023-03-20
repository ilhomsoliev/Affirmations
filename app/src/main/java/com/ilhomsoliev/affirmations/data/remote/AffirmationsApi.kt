package com.ilhomsoliev.affirmations.data.remote

import com.ilhomsoliev.affirmations.data.remote.dto.AffirmationResponse
import retrofit2.http.GET

interface AffirmationsApi {
    @GET("/")
    suspend fun getAffirmation(): AffirmationResponse
}