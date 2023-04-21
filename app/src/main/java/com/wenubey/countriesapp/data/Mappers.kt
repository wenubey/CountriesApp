package com.wenubey.countriesapp.data

import com.wenubey.CountriesQuery
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