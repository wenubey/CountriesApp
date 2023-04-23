package com.wenubey.countriesapp.data.remote
data class CountryDto(
    val name: NameDto?,
    val capital: List<String>?,
    val population: Int?,
    val currencies: Map<String, CurrencyDetailDto>?,
    val subregion: String?,
    val languages: Map<String, String>?,
    val continents: List<String>?,
)

data class NameDto(
    val common: String?,
    val official: String?,
    val nativeName: NativeNameDto?
)

data class NativeNameDto(
    val official: String?,
    val common: String?
)


data class CurrencyDetailDto(
    val name: String?,
    val symbol: String?,
)
