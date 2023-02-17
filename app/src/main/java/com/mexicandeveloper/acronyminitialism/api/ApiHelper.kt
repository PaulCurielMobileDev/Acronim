package com.mexicandeveloper.acronyminitialism.api

import com.mexicandeveloper.acronyminitialism.models.AbbreviationResponse
import retrofit2.Response
import retrofit2.http.Query

interface ApiHelper {
    suspend fun getAbreviationOrAcronim(
        sf: String,
        lf: String
    ): Response<List<AbbreviationResponse>>
}