package com.wenubey.countriesapp.presentation

import com.wenubey.countriesapp.data.remote.CountryDto

data class CountriesState(
    val countries: List<CountryDto> = emptyList(),
    val isLoading: Boolean =false,
    val selectedContinent: String = "",
    val selectedNumber: Int = 0,
)
