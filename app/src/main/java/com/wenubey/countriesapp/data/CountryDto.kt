package com.wenubey.countriesapp.data
data class CountryDto(
    val name: Name,
    val capital: List<String> = listOf("No info found"),
    val population: Int = 0,
    val currencies: Map<String, CurrencyDetail>,
    val subregion: String = "No info found",
    val languages: Map<String, String> = mapOf("No info found" to "No info found"),
    val continents: List<String> = listOf("No info found"),
)

data class Name(
    val common: String = "No info found",
    val official: String = "No info found",
    val nativeName: NativeName
)

data class NativeName(
    val official: String = "No info found",
    val common: String = "No info found"
)


data class CurrencyDetail(
    val name: String = "No info found",
    val symbol: String = "No info found",
)
