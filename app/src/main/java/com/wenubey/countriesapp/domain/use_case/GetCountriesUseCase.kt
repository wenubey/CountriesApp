package com.wenubey.countriesapp.domain.use_case

import com.wenubey.countriesapp.domain.CountryClient
import com.wenubey.countriesapp.domain.SimpleCountry

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {

    suspend fun execute(): List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }
}