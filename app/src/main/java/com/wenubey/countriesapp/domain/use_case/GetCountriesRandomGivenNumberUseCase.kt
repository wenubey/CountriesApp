package com.wenubey.countriesapp.domain.use_case

import com.wenubey.countriesapp.domain.CountryClient
import com.wenubey.countriesapp.domain.SimpleCountry

class GetCountriesRandomGivenNumberUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(numCountries: Int): List<SimpleCountry> {
        return countryClient
            .getCountriesRandomGivenNumber(numCountries)
    }
}