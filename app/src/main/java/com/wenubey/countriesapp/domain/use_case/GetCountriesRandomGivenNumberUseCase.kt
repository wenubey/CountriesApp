package com.wenubey.countriesapp.domain.use_case


import com.wenubey.countriesapp.data.CountryDto
import com.wenubey.countriesapp.domain.CountryClient

class GetCountriesRandomGivenNumberUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(numCountries: Int): List<CountryDto> {
        return countryClient
            .getCountriesRandomGivenNumber(numCountries)
    }
}