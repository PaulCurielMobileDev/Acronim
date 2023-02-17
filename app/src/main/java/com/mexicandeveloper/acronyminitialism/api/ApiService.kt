package com.mexicandeveloper.acronyminitialism.api

import com.mexicandeveloper.acronyminitialism.models.AbbreviationResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("software/acromine/dictionary.py")
    suspend fun getAbreviationOrAcronim(
        @Query("sf") sf: String,
        @Query("lf") lf: String
    ): Response<List<AbbreviationResponse>>
}