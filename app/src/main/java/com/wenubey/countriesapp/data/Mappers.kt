package com.wenubey.countriesapp.data

import com.wenubey.CountriesInContinentQuery
import com.wenubey.CountriesQuery
import com.wenubey.CountriesRandomGivenNumberQuery
import com.wenubey.CountryQuery
import com.wenubey.countriesapp.domain.DetailedCountry
import com.wenubey.countriesapp.domain.SimpleCountry

fun CountryQuery.Country.toDetailCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No information found!",
        currency = currency ?: "No information found!",
        languages = languages.map { it.name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No information found!",
    )
}

fun CountriesInContinentQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No information found!",
    )
}

fun CountriesRandomGivenNumberQuery.Country.toCountryName(): String {
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

fun List<CountryDto>.toListOfDetailedCountry(): List<DetailedCountry> = this.map { it.toDetailedCountry() }