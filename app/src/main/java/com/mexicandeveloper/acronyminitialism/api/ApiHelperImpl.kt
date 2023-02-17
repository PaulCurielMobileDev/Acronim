package com.mexicandeveloper.acronyminitialism.api

import com.mexicandeveloper.acronyminitialism.models.AbbreviationResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAbreviationOrAcronim(
        sf: String,
        lf: String
    ): Response<List<AbbreviationResponse>> = apiService.getAbreviationOrAcronim(sf, lf)
}