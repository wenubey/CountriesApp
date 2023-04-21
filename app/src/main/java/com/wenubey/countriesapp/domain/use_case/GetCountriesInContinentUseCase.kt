package com.wenubey.countriesapp.domain.use_case

import com.wenubey.countriesapp.domain.CountryClient
import com.wenubey.countriesapp.domain.SimpleCountry

class GetCountriesInContinentUseCase(
    private val countryClient: CountryClient
) {

    suspend fun execute(code: String): List<SimpleCountry> {
        return countryClient
            .getCountryInContinent(code)
            .sortedBy { it.name }
    }
}