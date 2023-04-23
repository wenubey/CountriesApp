package com.wenubey.countriesapp.domain

import com.wenubey.countriesapp.data.remote.CountryDto
import kotlinx.coroutines.flow.Flow


interface CountryClient {
    suspend fun getCountryInContinent(code: String): Flow<List<CountryDto>>

}