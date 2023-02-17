package com.mexicandeveloper.acronyminitialism.repository

import com.mexicandeveloper.acronyminitialism.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getAbreviationOrAcronim(
        abbreviation: String,
        fullforms: String
    ) = apiHelper.getAbreviationOrAcronim(abbreviation, fullforms)
}