package com.wenubey.countriesapp.domain.use_case

import com.wenubey.countriesapp.data.CountryDto
import com.wenubey.countriesapp.domain.CountryClient

class GetCountriesInContinentUseCase(
    private val countryClient: CountryClient
) {

    suspend fun execute(code: String, numCountries: Int): List<CountryDto> {
        return countryClient
            .getCountryInContinent(code, numCountries)
            .sortedBy { it.name.common }
    }
}