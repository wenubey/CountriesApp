package com.wenubey.countriesapp.domain.use_case

import com.wenubey.countriesapp.domain.CountryClient
import com.wenubey.countriesapp.domain.DetailedCountry

class GetCountryUseCase(
    private val countryClient: CountryClient
) {

    suspend fun execute(code: String): DetailedCountry? {
        return countryClient.getCountry(code)
    }
}