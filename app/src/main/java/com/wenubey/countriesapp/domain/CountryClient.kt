package com.wenubey.countriesapp.domain

import com.wenubey.countriesapp.data.CountryDto



interface CountryClient {
    suspend fun getCountryInContinent(code: String): List<CountryDto>

}