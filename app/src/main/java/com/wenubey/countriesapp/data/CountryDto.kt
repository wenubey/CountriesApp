package com.wenubey.countriesapp.data
data class CountryDto(
    val name: Name?,
    val capital: List<String>?,
    val population: Int?,
    val currencies: Map<String, CurrencyDetail>?,
    val subregion: String?,
    val languages: Map<String, String>?,
    val continents: List<String>?,
)

data class Name(
    val common: String?,
    val official: String?,
    val nativeName: NativeName?
)

data class NativeName(
    val official: String?,
    val common: String?
)


data class CurrencyDetail(
    val name: String?,
    val symbol: String?,
)
