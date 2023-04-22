package com.wenubey.countriesapp.data

import com.wenubey.CountriesInContinentQuery
import com.wenubey.countriesapp.domain.DetailedCountry


fun CountriesInContinentQuery.Country.toCountryName(): String {
    return this.name
}


fun CountryDto.toDetailedCountry(): DetailedCountry{
    return DetailedCountry(
        name =  name.official,
        capital = capital?.firstOrNull() ?: "No info found",
        currency = currencies.entries.first().key, // for switzerland I get CHF
        languages = languages.values.toList(),
        continent = continents?.first() ?: "No info found"
    )
}

